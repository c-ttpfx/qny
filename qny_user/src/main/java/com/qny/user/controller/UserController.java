package com.qny.user.controller;

import com.qny.user.annotation.PassToken;
import com.qny.user.annotation.UserLoginToken;
import com.qny.user.entity.User;
import com.qny.user.utils.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author Knight
 * @since 2023/10/26
 **/

@RestController
@RequestMapping("/user")
public class UserController {

    @PassToken
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        HashMap<String, String> map = new HashMap<>();
        String token = JwtUtil.getToken(user);
        map.put("token", token);
        map.put("user", user.getUsername());
        return map.toString();
    }

    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }
}
