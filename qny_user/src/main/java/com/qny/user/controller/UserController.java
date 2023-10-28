package com.qny.user.controller;

import com.qny.user.annotation.PassToken;
import com.qny.user.annotation.UserLoginToken;
import com.qny.user.entity.User;
import com.qny.user.utils.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Knight
 * @since 2023/10/26
 **/

@RestController
@RequestMapping("/user")
public class UserController {

    @PassToken
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>();
        // TODO 将数据库加密密码与用户明文密码做对比,这里简单比较
        if ("admin".equals(user.getUsername()) && "123456".equals(user.getPassword())) {
            // 生成JWT令牌
            String token = JwtUtil.getToken(user);
            map.put("token", token);
            return map;
        }
        map.put("error", "账号密码有误");
        return map;
    }

    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }
}
