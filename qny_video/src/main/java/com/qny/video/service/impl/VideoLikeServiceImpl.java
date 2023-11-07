package com.qny.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qny.common.utils.RedisUtils;
import com.qny.video.constant.RedisConstant;
import com.qny.video.domain.model.VideoLikeModel;
import com.qny.video.mapper.VideoLikeMapper;
import com.qny.video.service.VideoLikeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ttpfx
 * @since 2023/10/29
 */
@Service
public class VideoLikeServiceImpl extends ServiceImpl<VideoLikeMapper, VideoLikeModel> implements VideoLikeService {
    @Resource
    private VideoLikeMapper videoLikeMapper;
    @Resource
    private RedisUtils redisUtils;

    @Override
    public Long getVideoLikeCount(Long videoId) {
        if (videoId == null) {
            return 0L;
        }
        String key = String.format(RedisConstant.VIDEO_LIKE_COUNT_KEY, videoId);
        // 首先查询缓存
        if (redisUtils.hasKey(key)) {
            return redisUtils.getLong(key);
        }
        // 通过视频id查询数据库
        Long count = videoLikeMapper.selectCount(Wrappers
                .<VideoLikeModel>lambdaQuery()
                .eq(VideoLikeModel::getVideoId, videoId));
        redisUtils.set(key, count, 600);
        return count;
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
        boolean flag = videoLikeMapper.insert(videoLikeModel) > 0;
        if (flag) {
            // 操作成功删除缓存
            String key = String.format(RedisConstant.USER_VIDEO_LIKE_KEY, userId, videoId);
            redisUtils.del(key);
            key = String.format(RedisConstant.VIDEO_LIKE_COUNT_KEY, videoId);
            redisUtils.del(key);
        }
        return flag;
    }

    @Override
    public Boolean subVideoLikeCount(Long videoId, Long userId) {
        if (videoId == null || userId == null) {
            return false;
        }
        // 删除记录
        boolean flag = videoLikeMapper.delete(Wrappers
                .<VideoLikeModel>lambdaQuery()
                .eq(VideoLikeModel::getVideoId, videoId)
                .eq(VideoLikeModel::getUserId, userId)) > 0;
        if (flag) {
            // 操作成功删除缓存
            String key = String.format(RedisConstant.USER_VIDEO_LIKE_KEY, userId, videoId);
            redisUtils.del(key);
            key = String.format(RedisConstant.VIDEO_LIKE_COUNT_KEY, videoId);
            redisUtils.del(key);
        }
        return flag;
    }

    @Override
    public Boolean isLike(Long videoId, Long userId) {
        if (videoId == null || userId == null) {
            return false;
        }
        // 首先查询缓存
        String key = String.format(RedisConstant.USER_VIDEO_LIKE_KEY, userId, videoId);
        if (redisUtils.hasKey(key)){
            return (Boolean) redisUtils.get(key);
        }
        // 查询数据库
        boolean flag =  videoLikeMapper.selectCount(Wrappers
                .<VideoLikeModel>lambdaQuery()
                .eq(VideoLikeModel::getVideoId, videoId)
                .eq(VideoLikeModel::getUserId, userId)) > 0;
        // 加入缓存
        redisUtils.set(key,flag,600);
        return flag;
    }

    @Override
    public List<VideoLikeModel> getVideoIds(Long userId) {
        LambdaQueryWrapper<VideoLikeModel> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(userId != null, VideoLikeModel::getUserId, userId);
        return videoLikeMapper.selectList(wrapper);
    }
}
