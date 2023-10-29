package com.qny.video.controller;

import com.qny.video.domain.entity.Result;
import com.qny.video.service.VideoCollectService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * 用于提供视频收藏接口
 *
 * @author ttpfx
 * @since 2023/10/29
 */
@RestController
@RequestMapping("/videoCollect")
public class VideoCollectController {

    @Resource
    private VideoCollectService videoCollectService;

    /**
     * 视频收藏次数
     * @return 视频收藏次数
     */
    @GetMapping("/getCollectCount")
    public Result getVideoCollectCount(@RequestParam("videoId")
                                       @NotNull(message = "视频id不能为空")
                                       @NotNull Long videoId) {
        Long count = videoCollectService.getVideoCollectCount(videoId);
        return Result.ok(count);
    }
    /**
     * 视频收藏
     * @return Result
     */
    @PutMapping("/addCollectCount")
    public Result addVideoCollect(@RequestParam("videoId")
                                       @NotNull(message = "视频id不能为空")
                                       @NotNull Long videoId) {
        // 用户id
        Long userId= 111L;
        Boolean isOK = videoCollectService.addVideoCollect(videoId,userId);
        return isOK ? Result.ok():Result.fail();
    }
    /**
     * 取消视频收藏
     * @return Result
     */
    @PutMapping("/subCollectCount")
    public Result subVideoCollect(@RequestParam("videoId")
                                       @NotNull(message = "视频id不能为空")
                                       @NotNull Long videoId) {
        // 用户id
        Long userId= 111L;
        Boolean isOK = videoCollectService.subVideoCollect(videoId,userId);
        return isOK ? Result.ok():Result.fail();
    }

    /**
     * 用户视频是否收藏
     *
     * @param videoId 视频id
     * @return Result
     */
    @GetMapping("/isCollect")
    public Result isCollect(@RequestParam("videoId")
                         @NotNull(message = "视频id不能为空")
                         @NotNull Long videoId) {
        // 获取用户id
        Long userId = 111L;
        Boolean isLike = videoCollectService.isCollect(videoId, userId);
        return Result.ok(isLike);
    }
}
