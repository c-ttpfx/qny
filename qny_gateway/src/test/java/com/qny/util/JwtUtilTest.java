package com.qny.util;

import com.qny.video.domain.model.UserModel;
import com.qny.video.utils.JwtUtil;

/**
 * @author Knight
 * @since 2023/11/1
 **/

public class JwtUtilTest {
    public static void main(String[] args) {
        UserModel userModel = new UserModel();
        userModel.setUserId(1L);
        userModel.setName("admin");
        userModel.setPassword("123456");
        String token = JwtUtil.generateToken(userModel, 60);
        System.out.println("token=>" + token);

        UserModel decode = JwtUtil.decode(token);
        System.out.println(decode.getName() + "===" + decode.getUserId());
    }
}
