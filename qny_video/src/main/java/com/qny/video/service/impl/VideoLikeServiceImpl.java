package com.qny.video.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qny.video.domain.model.VideoLikeModel;
import com.qny.video.mapper.VideoLikeMapper;
import com.qny.video.service.VideoLikeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ttpfx
 * @since 2023/10/29
 */
@Service
public class VideoLikeServiceImpl extends ServiceImpl<VideoLikeMapper, VideoLikeModel> implements VideoLikeService {
    @Resource
    private VideoLikeMapper videoLikeMapper;

    @Override
    public Long getVideoLikeCount(Long videoId){
        if (videoId == null){
            return 0L;
        }
        // 通过视频id查询数据库
        return videoLikeMapper.selectCount(Wrappers
                .<VideoLikeModel>lambdaQuery()
                .eq(VideoLikeModel::getVideoId, videoId));
    }

    @Override
    public Boolean addVideoLikeCount(Long videoId, Long userId) {
        if (videoId == null || userId == null) {
            return false;
        }
        VideoLikeModel videoLikeModel = new VideoLikeModel();
        videoLikeModel.setUserId(userId);
        videoLikeModel.setVideoId(videoId);
        videoLikeModel.setLikeTime(System.currentTimeMillis());
        // 新增记录
        return videoLikeMapper.insert(videoLikeModel) > 0;
    }

    @Override
    public Boolean subVideoLikeCount(Long videoId, Long userId)  {
        if (videoId == null || userId == null) {
            return false;
        }
        // 删除记录
        return videoLikeMapper.delete(Wrappers
                .<VideoLikeModel>lambdaQuery()
                .eq(VideoLikeModel::getVideoId, videoId)
                .eq(VideoLikeModel::getUserId, userId)) > 0;
    }

    @Override
    public Boolean isLike(Long videoId, Long userId) {
        if (videoId == null || userId == null){
            return false;
        }
        // 查询数据库
        return videoLikeMapper.selectCount(Wrappers
                .<VideoLikeModel>lambdaQuery()
                .eq(VideoLikeModel::getVideoId, videoId)
                .eq(VideoLikeModel::getUserId, userId)) > 0;
    }
}
