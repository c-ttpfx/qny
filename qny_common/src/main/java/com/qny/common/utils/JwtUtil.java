package com.qny.common.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.qny.common.constant.JWTConstants;
import com.qny.common.domain.model.UserModel;
import com.qny.common.exception.GeneralException;
import org.joda.time.DateTime;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Knight
 * @since 2023/10/26
 * JWT工具类
 **/

public class JwtUtil {

    public static final String JWT_KEY = JWTConstants.JWT_KEY;

    /**
     * 生成密钥
     * @return 生成密钥
     */
    private static Algorithm getAlgorithm() {
        return Algorithm.HMAC256(JWT_KEY);
    }

    /**
     * 生成token
     *
     * @param user 用户对象
     * @param expireMinutes 过期时间，单位为 秒
     * @return token jwtToken
     */
    public static String generateToken(UserModel user, int expireMinutes) {
        return generateToken(String.valueOf(user.getUserId()), user.getName(), expireMinutes);
    }

    public static String generateToken(String userId, String userName, int expireMinutes) {
        return JWT.create()
                .withClaim(JWTConstants.JWT_KEY_USER_NAME, userName)
                .withClaim(JWTConstants.JWT_KEY_ID, userId)
                .withExpiresAt(DateTime.now().plusSeconds(expireMinutes).toDate())
                .sign(getAlgorithm());
    }

    /**
     * 解码token
     *
     * @param token jwtToken
     * @return 用户信息
     */
    public static UserModel decode(String token) {
        UserModel user = new UserModel();
        DecodedJWT decodedJWT = JWT.require(getAlgorithm()).build().verify(token);
        Map<String, Claim> jwt = decodedJWT.getClaims();
        String userName = jwt.get(JWTConstants.JWT_KEY_USER_NAME).asString();
        String userId = jwt.get(JWTConstants.JWT_KEY_ID).asString();
        user.setUserId(Long.parseLong(userId));
        user.setName(userName);
        return user;
    }

    public static UserModel decode(HttpServletRequest request) {
        String token = request.getHeader(JWTConstants.JWT_REQUEST_HEADER_KEY);
        if (StringUtils.isEmpty(token)) {
            throw new GeneralException("token为空");
        }
        return decode(token);
    }
}
