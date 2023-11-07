package com.qny.video.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qny.common.domain.model.UserModel;
import com.qny.common.utils.RedisUtils;
import com.qny.video.constant.RedisConstant;
import com.qny.video.domain.dto.VideoShareDTO;
import com.qny.video.domain.model.UserFollowModel;
import com.qny.video.domain.model.VideoCollectModel;
import com.qny.video.domain.model.VideoMetadataModel;
import com.qny.video.domain.model.VideoShareModel;
import com.qny.video.domain.vo.VideoShareVO;
import com.qny.video.fegin.UserFeginApi;
import com.qny.video.mapper.VideoCollectMapper;
import com.qny.video.mapper.VideoShareMapper;
import com.qny.video.service.VideoCollectService;
import com.qny.video.service.VideoMetadataService;
import com.qny.video.service.VideoShareService;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ttpfx
 * @since 2023/10/29
 */
@Service
public class VideoShareServiceImpl extends ServiceImpl<VideoShareMapper, VideoShareModel> implements VideoShareService {

    @Resource
    private VideoShareMapper videoShareMapper;
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private VideoMetadataService videoMetadataService;
    @Resource
    private UserFeginApi userFeginApi;

    @Override
    public Boolean share(VideoShareDTO videoShareDTO) {
        VideoShareModel videoShareModel = new VideoShareModel();
        BeanUtils.copyProperties(videoShareDTO, videoShareModel);
        videoShareModel.setHasRead((byte) 0);
        // 设置封面
        VideoMetadataModel videoMetadataModel = videoMetadataService.getById(videoShareModel.getVideoId());
        videoShareModel.setVideoCover(videoMetadataModel.getThumbnailPath());
        // 保存消息
        boolean flag = videoShareMapper.insert(videoShareModel) > 0;
        // 删除缓存
        String key = String.format(RedisConstant.ALL_USER_SHARE_KEY, videoShareModel.getToUserId(), videoShareModel.getVideoId());
        redisUtils.del(key);
        return flag;
    }

    @Override
    public List<VideoShareVO> getAllShareVideo(Long userId) {
        if (userId == null) {
            return null;
        }
        // 先查询缓存
        String key = String.format(RedisConstant.ALL_USER_SHARE_KEY, userId);
        if (redisUtils.hasKey(key)) {
            return (List<VideoShareVO>) redisUtils.get(key);
        }
        // 查询数据库
        List<VideoShareModel> videoShareModelList = videoShareMapper.selectList(Wrappers
                .<VideoShareModel>lambdaQuery()
                .eq(VideoShareModel::getToUserId, userId));
        // 获取所有分享者id
        Long[] shareIds = videoShareModelList
                .stream()
                .map(VideoShareModel::getUserId).
                toArray(Long[]::new);
        // 远程调用查询用户信息
        List<UserModel> userModels = userFeginApi.getUserByIds(shareIds).getData();
        // 获取用户id->用户的映射
        Map<Long, UserModel> userModelMap = userModels
                .stream()
                .collect(Collectors
                        .toMap(UserModel::getUserId, x -> x));
        // Model->VO的转换
        List<VideoShareVO> videoShareVOList = videoShareModelList
                .stream()
                .sorted((a, b) -> Math.toIntExact(b.getShareTime() - a.getShareTime()))
                .map(videoShareModel -> {
                    VideoShareVO videoShareVO = new VideoShareVO();
                    BeanUtils.copyProperties(videoShareModel,videoShareVO,"shareTime");
                    // 设置分享者信息
                    videoShareVO.setUser(userModelMap.get(videoShareModel.getUserId()));
                    // 类型转换
                    videoShareVO.setUserId(String.valueOf(videoShareModel.getUserId()));
                    videoShareVO.setToUserId(String.valueOf(videoShareModel.getToUserId()));
                    videoShareVO.setVideoId(String.valueOf(videoShareModel.getVideoId()));
                    videoShareVO.setShareTime(new Date(videoShareModel.getShareTime()));
                    return videoShareVO;
                })
                .collect(Collectors.toList());
        // 加入缓存
        redisUtils.set(key,videoShareVOList,600);
        return videoShareVOList;
    }


}
