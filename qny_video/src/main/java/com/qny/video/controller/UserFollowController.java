package com.qny.video.controller;

import com.qny.common.domain.entity.Result;
import com.qny.common.domain.model.UserModel;
import com.qny.common.utils.JwtUtil;
import com.qny.video.domain.vo.UserVO;
import com.qny.video.service.UserFollowService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author ttpfx
 * @since 2023/11/6
 */
@RestController
@RequestMapping("/userFollow")
public class UserFollowController {

    @Resource
    private UserFollowService userFollowService;

    /**
     * 关注
     * @param followUserId 关注者id
     * @return Result
     */
    @PostMapping("/follow")
    public Result follow(@RequestParam("followUserId")
                         @NotNull(message = "关注者id不能为null")
                         Long followUserId) {
        Long userId = Long.valueOf(JwtUtil.getUserID());
        boolean flag = userFollowService.follow(userId,followUserId);
        return Result.ok(flag);
    }
    /**
     * 取消关注
     * @param followUserId 关注者id
     * @return Result
     */
    @DeleteMapping("/follow")
    public Result cancelFollow(@RequestParam("followUserId")
                         @NotNull(message = "关注者id不能为null")
                         Long followUserId) {
        Long userId = Long.valueOf(JwtUtil.getUserID());
        boolean flag = userFollowService.cancelFollow(userId,followUserId);
        return Result.ok(flag);
    }

    /**
     * 查询所有关注的用户
     * @return Result
     */
    @GetMapping("/allFollowInfo")
    public Result allFollowInfo(){
        Long userId = Long.valueOf(JwtUtil.getUserID());
        List<UserVO> userModels = userFollowService.allFollowInfo(userId);
        return Result.ok(userModels);
    }

    /**
     * 条件查询所有关注的用户
     * @param name 名称
     * @return Result
     */
    @GetMapping("/searchFollow")
    public Result searchFollow(@RequestParam("name") String name){
        Long userId = Long.valueOf(JwtUtil.getUserID());
        List<UserVO> userModels = userFollowService.searchFollow(userId,name);
        return Result.ok(userModels);
    }
}
