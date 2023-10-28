package com.qny.gateway.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

/**
 * @author Knight
 * @since 2023/10/29
 *
 * 全局过滤器
 **/
@Component
public class GatewayGlobalFilter implements GlobalFilter {

    public static String[]  excludeUrls = new String[]{"/user/login"};

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        URI uri = request.getURI();
        // 1、如果是登录请求，直接放行
        if (Arrays.stream(excludeUrls).anyMatch(url -> url.equals(uri.getPath()))) {
            return chain.filter(exchange); // 放行
        }
        // 如果不是登录直接验证 token
        List<String> list = request.getHeaders().get("token");
        if (list == null || list.isEmpty()) {
            response.setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return response.setComplete();
        }
        String token = list.get(0);

        // TODO 这里可以使用 Redis 或者将数据放在Thread里面
        // 这里新建一个 user 用于测试
        // 验证 token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("123456")).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            // 2、验证失败
            response.setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return response.setComplete();
        }
        // 3、验证成功
        return chain.filter(exchange);
    }
}
