package com.qny.video.controller;

import com.qny.common.domain.entity.Result;
import com.qny.video.service.VideoLikeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * 用于提供视频点赞接口
 *
 * @author ttpfx
 * @since 2023/10/29
 */
@RestController
@RequestMapping("/videoLike")
public class VideoLikeController {

    @Resource
    private VideoLikeService videoLikeService;

    /**
     * 获取对应视频点赞次数
     *
     * @return Result
     */
    @GetMapping("/getLikeCount")
    public Result getLikeCount(@RequestParam("videoId") Long videoId) {
        Long count = videoLikeService.getVideoLikeCount(videoId);
        return Result.ok(count);
    }

    /**
     * 增加对应视频点赞次数
     *
     * @return Result
     */
    @PutMapping("/addLikeCount")
    public Result addLikeCount(@RequestParam("videoId")
                               @NotNull(message = "视频id不能为空")
                               Long videoId) {
        // 获取用户id
        Long userId = 111L;
        boolean isOk = videoLikeService.addVideoLikeCount(videoId, userId);
        return isOk ? Result.ok() : Result.fail();

    }

    /**
     * 减少对应视频点赞次数
     *
     * @return Result
     */
    @PutMapping("/subLikeCount")
    public Result subLikeCount(@RequestParam("videoId")
                               @NotNull(message = "视频id不能为空")
                               @NotNull Long videoId) {
        // 获取用户id
        Long userId = 111L;
        boolean isOk = videoLikeService.subVideoLikeCount(videoId, userId);
        return isOk ? Result.ok() : Result.fail();
    }

    /**
     * 用户视频是否点赞
     *
     * @param videoId 视频id
     * @return Result
     */
    @GetMapping("/isLike")
    public Result isLike(@RequestParam("videoId")
                         @NotNull(message = "视频id不能为空")
                         @NotNull Long videoId) {
        // 获取用户id
        Long userId = 111L;
        boolean isLike = videoLikeService.isLike(videoId, userId);
        return Result.ok(isLike);
    }
}
