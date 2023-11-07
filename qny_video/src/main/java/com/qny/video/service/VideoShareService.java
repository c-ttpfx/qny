package com.qny.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qny.video.domain.dto.VideoShareDTO;
import com.qny.video.domain.model.VideoShareModel;
import com.qny.video.domain.vo.VideoShareVO;

import java.util.List;

/**
 * @author ttpfx
 * @since 2023/10/29
 */
public interface VideoShareService extends IService<VideoShareModel> {
    /**
     * 分享视频
     * @param videoShareDTO VideoShareDTO
     * @return true/false
     */
    Boolean share(VideoShareDTO videoShareDTO);

    /**
     * 获取所有的分享视频
     * @param userId 用户id
     * @return List<VideoShareVO>
     */
    List<VideoShareVO> getAllShareVideo(Long userId);
}
