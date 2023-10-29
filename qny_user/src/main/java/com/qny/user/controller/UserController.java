package com.qny.user.controller;

import com.qny.video.domain.entity.Result;
import com.qny.video.domain.model.User;
import com.qny.video.utils.JwtUtil;
import org.springframework.web.bind.annotation.*;

/**
 * @author Knight
 * @since 2023/10/26
 **/

@RestController
@RequestMapping("/user")
public class UserController {
    private static final int expireMinutes = 3600; // token 过期时间, 单位：秒

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        // TODO 将数据库加密密码与用户明文密码做对比,这里简单比较
        if (!("admin".equals(user.getName()) && "123456".equals(user.getPassword()))) { // 用户登录失败
            return Result.fail("账号密码有误");
        }
        // 生成JWT令牌
        String token = JwtUtil.generateToken(user, expireMinutes);
        return Result.ok(token);
    }

    @GetMapping("/getMessage")
    public String getMessage(){
        System.out.println("进入 getMessage！");
        return "你已通过验证";
    }
}
