package com.qny.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qny.video.domain.model.VideoLikeModel;
import com.qny.video.exception.VerifyException;

/**
 * @author ttpfx
 * @since 2023/10/29
 */
public interface VideoLikeService extends IService<VideoLikeModel> {
    /**
     * 获取对应视频点赞次数
     * @param videoId 视频id
     * @return 视频点赞次数
     */
    Long getVideoLikeCount(Long videoId) ;


    /**
     * 点赞视频
     * @param videoId 视频id
     * @param userId 用户id
     * @return 是否成功标志
     */
    Boolean addVideoLikeCount(Long videoId,Long userId);

    /**
     * 取消点赞
     * @param videoId 视频id
     * @param userId 用户id
     * @return 是否成功标志
     */
    Boolean subVideoLikeCount(Long videoId, Long userId);

    /**
     * 用户是否在对应视频下点赞
     * @param videoId 视频id
     * @param userId 用户id
     * @return true/false
     */
    Boolean isLike(Long videoId, Long userId);


}
