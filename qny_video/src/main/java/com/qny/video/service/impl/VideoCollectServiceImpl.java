package com.qny.video.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qny.video.domain.model.VideoCollectModel;
import com.qny.video.mapper.VideoCollectMapper;
import com.qny.video.service.VideoCollectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ttpfx
 * @since 2023/10/29
 */
@Service
public class VideoCollectServiceImpl extends ServiceImpl<VideoCollectMapper, VideoCollectModel> implements VideoCollectService {
    @Resource
    private VideoCollectMapper videoCollectMapper;

    @Override
    public Long getVideoCollectCount(Long videoId) {
        if (videoId == null){
            return 0L;
        }
        // 查询数据库
        return videoCollectMapper.selectCount(Wrappers
                .<VideoCollectModel>lambdaQuery()
                .eq(VideoCollectModel::getVideoId, videoId));
    }

    @Override
    public Boolean addVideoCollect(Long videoId, Long userId) {
        if (videoId == null || userId == null){
            return false;
        }
        VideoCollectModel videoCollectModel = new VideoCollectModel();
        videoCollectModel.setVideoId(videoId);
        videoCollectModel.setUserId(userId);
        videoCollectModel.setCollectTime(System.currentTimeMillis());
        // 插入数据
        return videoCollectMapper.insert(videoCollectModel) > 0;
    }

    @Override
    public Boolean subVideoCollect(Long videoId, Long userId) {
        if (videoId == null || userId == null){
            return false;
        }
        // 删除数据
        return videoCollectMapper.delete(Wrappers
                .<VideoCollectModel>lambdaQuery()
                .eq(VideoCollectModel::getVideoId, videoId)
                .eq(VideoCollectModel::getUserId, userId)) > 0;
    }

    @Override
    public Boolean isCollect(Long videoId, Long userId) {
        if (videoId == null || userId == null){
            return false;
        }
        // 查询数据库
        return videoCollectMapper.selectCount(Wrappers
                .<VideoCollectModel>lambdaQuery()
                .eq(VideoCollectModel::getVideoId, videoId)
                .eq(VideoCollectModel::getUserId, userId)) > 0;
    }

}
