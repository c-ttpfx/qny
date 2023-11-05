package com.qny.video.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qny.common.utils.RedisUtils;
import com.qny.video.constant.RedisConstant;
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
    @Resource
    private RedisUtils redisUtils;

    @Override
    public Long getVideoCollectCount(Long videoId) {
        if (videoId == null) {
            return 0L;
        }
        String key = String.format(RedisConstant.VIDEO_COLLECT_COUNT_KEY, videoId);
        // 查询是否命中缓存
        if (redisUtils.hasKey(key)) {
            return  redisUtils.getLong(key);
        }
        // 查询数据库
        Long count = videoCollectMapper.selectCount(Wrappers
                .<VideoCollectModel>lambdaQuery()
                .eq(VideoCollectModel::getVideoId, videoId));
        // 添加缓存
        redisUtils.set(key, count, 600);
        return count;
    }

    @Override
    public Boolean addVideoCollect(Long videoId, Long userId) {
        if (videoId == null || userId == null) {
            return false;
        }
        VideoCollectModel videoCollectModel = new VideoCollectModel();
        videoCollectModel.setVideoId(videoId);
        videoCollectModel.setUserId(userId);
        videoCollectModel.setCollectTime(System.currentTimeMillis());
        // 插入数据
        boolean flag = videoCollectMapper.insert(videoCollectModel) > 0;
        if (flag) {
            // 操作成功就删除缓存
            String key = String.format(RedisConstant.VIDEO_COLLECT_COUNT_KEY, videoId);
            redisUtils.del(key);
            key = String.format(RedisConstant.USER_VIDEO_COLLECT_KEY, userId, videoId);
            redisUtils.del(key);
        }
        return flag;
    }

    @Override
    public Boolean subVideoCollect(Long videoId, Long userId) {
        if (videoId == null || userId == null) {
            return false;
        }
        // 删除数据
        boolean flag = videoCollectMapper.delete(Wrappers
                .<VideoCollectModel>lambdaQuery()
                .eq(VideoCollectModel::getVideoId, videoId)
                .eq(VideoCollectModel::getUserId, userId)) > 0;
        if (flag) {
            // 操作成功就删除缓存
            String key = String.format(RedisConstant.VIDEO_COLLECT_COUNT_KEY, videoId);
            redisUtils.del(key);
            key = String.format(RedisConstant.USER_VIDEO_COLLECT_KEY, userId, videoId);
            redisUtils.del(key);
        }
        return flag;
    }

    @Override
    public Boolean isCollect(Long videoId, Long userId) {
        if (videoId == null || userId == null) {
            return false;
        }
        String key = String.format(RedisConstant.USER_VIDEO_COLLECT_KEY, userId, videoId);
        // 首先查询缓存
        if (redisUtils.hasKey(key)) {
             return (Boolean) redisUtils.get(key);
        }
        // 查询数据库
        boolean flag = videoCollectMapper.selectCount(Wrappers
                .<VideoCollectModel>lambdaQuery()
                .eq(VideoCollectModel::getVideoId, videoId)
                .eq(VideoCollectModel::getUserId, userId)) > 0;
        // 加入缓存
        redisUtils.set(key, flag,600);
        return flag;
    }

}
