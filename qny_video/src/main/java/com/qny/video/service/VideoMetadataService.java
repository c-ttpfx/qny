package com.qny.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qny.video.domain.dto.VideoMetadataDTO;
import com.qny.video.domain.model.VideoMetadataModel;
import com.qny.video.domain.vo.VideoInfoVO;

import java.util.List;

/**
 * @author ttpfx
 * @since 2023/10/27
 */
public interface VideoMetadataService extends IService<VideoMetadataModel> {

    /**
     * 往数据库插入一条记录
     * @param vmd VideoMetadataDTO
     * @return true/false
     */
    boolean save(VideoMetadataDTO vmd);

    /**
     * 返回一个随机的视频信息
     * @return VideoInfoVO
     */
    VideoInfoVO randomVideoInfo();

    List<VideoInfoVO> getVideoInfoByTag(String tag);

    /**
     * 通过id返回视频信息
     * @param videoId 视频id
     * @return VideoInfoVO
     */
    VideoInfoVO getVideoById(String videoId);

    /**
     * 搜索title返回视频信息
     * @param search 搜索值
     * @return VideoInfo List
     */
    List<VideoInfoVO> getVideoInfoByTitle(String search);

    List<VideoInfoVO> getVideoInfoByUserID(String userId);
}
