package com.qny.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.qny.video.constant.AuthConstant;
import com.qny.video.constant.AuthReturnMessage;
import com.qny.video.constant.JWTConstants;
import com.qny.video.domain.entity.Result;
import com.qny.video.domain.model.UserModel;
import com.qny.video.utils.JwtUtil;
import com.qny.video.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author Knight
 * @since 2023/10/29
 *
 * 全局过滤器
 **/
@Component
@Slf4j
public class GatewayGlobalFilter implements GlobalFilter {

    // gateway放行URI列表
    public static String[]  excludeUris = new String[]{
            "/user/login", "/user/logout", "/user/register", "/video/randomVideo"};

    // redis 过期时间
    private static final int overdueTime = 20;

    private static final int expireMinutes = 3600; // token 过期时间, 单位：秒

    @Resource
    private RedisUtils redisUtils;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String url = request.getURI().getPath();
        System.out.println("接收到请求：" + url);

        // 1、属于放行列表，直接放行
        if (Arrays.asList(excludeUris).contains(url)) {
            return chain.filter(exchange); // 放行
        }
        // 如果不是登录直接验证 token
        String token = request.getHeaders().getFirst(JWTConstants.JWT_REQUEST_HEADER_KEY);
        if (!StringUtils.hasText(token)) {
            return authError(response, AuthReturnMessage.AUTH_EXCEPTION_NULL);
        }

        try {

            //校验token并解析token
            UserModel user = JwtUtil.decode(token);

            // 登出，将当前 token 放入 redis 表示弃用
            if (url.equals(AuthConstant.URL_LOGOUT)) {
                redisUtils.set(AuthConstant.PREFIX_TOKEN_LAPSED + user.getName(), token, overdueTime);
                return authSuccess(response, AuthReturnMessage.AUTH_SUCCESS_LOGOUT);
            }

            // 判断token是否已弃用
            String sig = redisUtils.get(AuthConstant.PREFIX_TOKEN_LAPSED + user.getName()) + "";
            if (token.equals(sig)) {
                return authError(response, AuthReturnMessage.AUTH_EXCEPTION_LAPSED);
            }

            // 继续路由
            return chain.filter(exchange);
        } catch (TokenExpiredException tokenExpiredException) {
            // 处理过期的token
            return expiredToken(tokenExpiredException, exchange, url, token);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return authError(response, AuthReturnMessage.AUTH_EXCEPTION_FAIL);
        }
    }

    /**
     * token过期的处理
     */
    private Mono<Void> expiredToken(TokenExpiredException tokenExpiredException, ServerWebExchange exchange, String url, String token) {
        log.error(tokenExpiredException.getMessage(), tokenExpiredException);
        String userName = exchange.getRequest().getHeaders().getFirst(JWTConstants.JWT_REQUEST_KEY_USER_NAME);
        String userId = exchange.getRequest().getHeaders().getFirst(JWTConstants.JWT_REQUEST_KEY_ID);
        ServerHttpRequest request = exchange.getRequest();
        // String ip = request.getURI().getHost();
        ServerHttpResponse resp = exchange.getResponse();

        //登出
        if (url.equals(AuthConstant.URL_LOGOUT)) {
            return authError(resp, AuthReturnMessage.AUTH_EXCEPTION_EXPIRED);
        }
        //判断 token 是否已弃用
        String sig = redisUtils.get(AuthConstant.PREFIX_TOKEN_LAPSED + userName) + "";
        if (token.equals(sig)) {
            return authError(resp, AuthReturnMessage.AUTH_EXCEPTION_EXPIRED);
        }
        //续签
        String newToken = JwtUtil.generateToken(userId, userName, expireMinutes);
        return authSuccess(resp, new Result(HttpStatus.OK.value(), "token过期了", newToken));
    }

    /**
     * 认证错误输出
     *
     * @param resp 响应对象
     * @param mess 错误信息
     * @return
     */
    private Mono<Void> authError(ServerHttpResponse resp, String mess) {
        resp.setStatusCode(HttpStatus.UNAUTHORIZED);
        resp.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        DataBuffer buffer = resp.bufferFactory().wrap(mess.getBytes(StandardCharsets.UTF_8));
        return resp.writeWith(Flux.just(buffer));
    }

    private Mono<Void> authSuccess(ServerHttpResponse resp, Object mess) {
        resp.setStatusCode(HttpStatus.OK);
        resp.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        DataBuffer buffer = resp.bufferFactory().wrap(JSONObject.toJSONString(mess).getBytes(StandardCharsets.UTF_8));
        return resp.writeWith(Flux.just(buffer));
    }

    private Mono<Void> authSuccess(ServerHttpResponse resp, String mess) {
        resp.setStatusCode(HttpStatus.OK);
        resp.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        DataBuffer buffer = resp.bufferFactory().wrap(mess.getBytes(StandardCharsets.UTF_8));
        return resp.writeWith(Flux.just(buffer));
    }
}
