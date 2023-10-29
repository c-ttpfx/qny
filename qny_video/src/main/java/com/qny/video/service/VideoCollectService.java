package com.qny.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qny.video.domain.model.VideoCollectModel;

/**
 * @author ttpfx
 * @since 2023/10/29
 */
public interface VideoCollectService extends IService<VideoCollectModel> {
    /**
     * 返回对应视频收藏次数
     * @param videoId 视频id
     * @return Integer
     */
    Long getVideoCollectCount(Long videoId);

    /**
     * 视频收藏
     * @param videoId 视频id
     * @param userId 用户id
     * @return Boolean
     */
    Boolean addVideoCollect(Long videoId, Long userId);

    /**
     * 取消视频收藏
     * @param videoId 视频id
     * @param userId 用户id
     * @return Boolean
     */
    Boolean subVideoCollect(Long videoId, Long userId);

    /**
     * 视频是否被用户收藏
     * @param videoId 视频id
     * @param userId 用户id
     * @return Boolean
     */
    Boolean isCollect(Long videoId, Long userId);

}
