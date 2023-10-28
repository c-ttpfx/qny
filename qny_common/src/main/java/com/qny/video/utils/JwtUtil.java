package com.qny.video.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.qny.video.domain.model.User;

/**
 * @author Knight
 * @since 2023/10/26
 * JWT工具类
 **/

public class JwtUtil {
    /**
     * 生成JWT令牌
     * @param user 用户对象
     * @return JWT令牌
     */
    public static String getToken(User user) {
        String token = "";
        token = JWT.create().withAudience(user.getUserId().toString())
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }

    public static Long getUserID(String token) {
        return Long.parseLong(JWT.decode(token).getAudience().get(0));
    }
}
