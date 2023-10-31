package com.qny.user.controller;

import com.qny.user.service.UserService;
import com.qny.video.domain.entity.Result;
import com.qny.video.domain.model.UserModel;
import com.qny.video.utils.JwtUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author Knight
 * @since 2023/10/26
 **/

@RestController
@RequestMapping("/user")
public class UserController {
    private static final int expireMinutes = 3600; // token 过期时间, 单位：秒
    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody UserModel user) {
        // TODO 将数据库加密密码与用户明文密码做对比,这里简单比较
        if (!("admin".equals(user.getName()) && "123456".equals(user.getPassword()))) { // 用户登录失败
            return Result.fail("账号密码有误");
        }
        // 生成JWT令牌
        String token = JwtUtil.generateToken(user, expireMinutes);
        return Result.ok(token);
    }

    @GetMapping("/getMessage")
    public String getMessage() {
        System.out.println("进入 getMessage！");
        return "你已通过验证";
    }

    /**
     * 通过用户id查询User
     *
     * @param userId 用户id
     * @return User
     */
    @GetMapping("/getUserById")
    public Result<UserModel> getUserById(@RequestParam("userId") Long userId) {
        UserModel userModel = userService.getUserById(userId);
        return Result.ok(userModel);
    }

    /**
     * 通过用户ids查询Users
     *
     * @param userIds 用户id集合
     * @return List<UserModel>
     */
    @GetMapping("/getUserByIds")
    public Result<List<UserModel>> getUserByIds(@RequestParam("userIds") Long[] userIds) {
        if (userIds == null || userIds.length == 0) return Result.ok();
        List<Long> userList = new ArrayList<>(userIds.length);
        userList.addAll(Arrays.asList(userIds));
        List<UserModel> userModel = userService.getUserByIds(userList);
        return Result.ok(userModel);
    }
}
