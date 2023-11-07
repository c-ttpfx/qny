package com.qny.video.controller;

import com.qny.common.domain.entity.Result;
import com.qny.common.domain.model.UserModel;
import com.qny.common.utils.JwtUtil;
import com.qny.video.domain.model.VideoLikeModel;
import com.qny.video.domain.model.VideoMetadataModel;
import com.qny.video.domain.vo.VideoInfoVO;
import com.qny.video.fegin.UserFeginApi;
import com.qny.video.service.VideoLikeService;
import com.qny.video.service.VideoMetadataService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

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
    @Resource
    private VideoMetadataService videoMetadataService;
    @Resource
    private UserFeginApi userFeginApi;

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
        Long userId = Long.valueOf(JwtUtil.getUserID());
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
        Long userId = Long.valueOf(JwtUtil.getUserID());
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
        Long userId = Long.valueOf(JwtUtil.getUserID());
        boolean isLike = videoLikeService.isLike(videoId, userId);
        return Result.ok(isLike);
    }

    /**
     * 获取用户喜欢的视频ID
     * @return Ids
     */
    @GetMapping("/getVideoIds")
    public Result<List<VideoInfoVO>> getVideoIds() {
        Long userId = Long.valueOf(JwtUtil.getUserID());
        List<Long> ids = videoLikeService.getVideoIds(userId)
                .stream().map(VideoLikeModel::getVideoId).collect(Collectors.toList());
        List<VideoMetadataModel> videoMetadataModels = videoMetadataService.listByIds(ids);
        List<VideoInfoVO> res = videoMetadataModels.stream().map(metadata -> {
            VideoInfoVO videoInfoVO = new VideoInfoVO();
            videoInfoVO.setVideoId(metadata.getId().toString());
            videoInfoVO.setVideoM3U8Url(metadata.getFilePath());
            videoInfoVO.setVideoTitle(metadata.getTitle());
            UserModel user = userFeginApi.getUserById(Long.parseLong(metadata.getUploaderId())).getData();
            videoInfoVO.setVideoAuthor(user.getName());
            videoInfoVO.setUser(user);
            videoInfoVO.setPublishTime(metadata.getUploadTime());
            return videoInfoVO;
        }).collect(Collectors.toList());
        return Result.ok(res);
    }
}
