package com.qny.user.controller;

import com.qiniu.util.Md5;
import com.qny.user.service.UserService;
import com.qny.common.domain.entity.Result;
import com.qny.common.domain.model.UserModel;
import com.qny.common.utils.JwtUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public Result<Object> login(@RequestBody UserModel user) {
        UserModel userDB = userService.query().eq("name", user.getName()).one();
        if (userDB == null) {
            return Result.fail("账号不存在");
        }
        if (!Md5.md5(user.getPassword().getBytes()).equals(userDB.getPassword())) {
            return Result.fail("密码有误");
        }
        // 生成JWT令牌
        String token = JwtUtil.generateToken(userDB, expireMinutes);

        return Result.ok(token);
    }

    @PostMapping("/register")
    public Result<Object> register(@RequestBody UserModel user) {
        // 对密码进行二次加密
        user.setPassword(Md5.md5(user.getPassword().getBytes()));
        boolean save = userService.save(user);
        if (save) {
            return Result.ok("注册成功");
        } else {
            return Result.fail("注册失败");
        }
    }

    @PostMapping("/verify")
    public Result<Object> verifyToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        UserModel user = JwtUtil.decode(token);
        if (user.getUserId() == null || user.getName() == null) {
            return Result.fail("token解析失败");
        }
        UserModel userDB = userService.query().eq("name", user.getName()).one();
        if (userDB == null) {
            return Result.fail("不存在该用户");
        }
        // 因为这里会被网关拦截，并且验证token的正确性，所以接口只需要验证在数据库是否有该用户
        return Result.ok("token验证成功");
    }

    @GetMapping("/getMessage")
    public String getMessage() {
        System.out.println("进入 getMessage！");
        return "你已通过验证";
    }

    @PostMapping("/getMessage")
    public Result<String> getMessageTest() {
        System.out.println("进入 getMessageTest！");
        return  Result.ok("你已通过验证");
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
