package com.qny.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qny.common.domain.model.UserModel;
import com.qny.common.utils.RedisUtils;
import com.qny.video.constant.RedisConstant;
import com.qny.video.domain.dto.VideoMetadataDTO;
import com.qny.video.domain.model.VideoMetadataModel;
import com.qny.video.domain.vo.VideoInfoVO;
import com.qny.video.enumeration.VideoSortTag;
import com.qny.video.fegin.UserFeginApi;
import com.qny.video.mapper.VideoMetadataMapper;
import com.qny.video.service.VideoCollectService;
import com.qny.video.service.VideoCommentService;
import com.qny.video.service.VideoLikeService;
import com.qny.video.service.VideoMetadataService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author ttpfx
 * @since 2023/10/27
 */
@Service
public class VideoMetadataServiceImpl extends ServiceImpl<VideoMetadataMapper, VideoMetadataModel> implements VideoMetadataService {

    @Resource
    private VideoMetadataMapper videoMetadataMapper;
    @Resource
    private VideoLikeService videoLikeService;
    @Resource
    private VideoCollectService videoCollectService;
    @Resource
    private VideoCommentService videoCommentService;
    @Resource
    private UserFeginApi userFeginApi;
    @Resource
    private RedisUtils redisUtils;

    @Override
    public boolean save(VideoMetadataDTO vmd) {
        VideoMetadataModel vmm = new VideoMetadataModel();
        BeanUtils.copyProperties(vmd, vmm, "tags");
        // 设置缩略图
        vmm.setThumbnailPath("");
        // 标志转换
        String tags = vmd.getTags().stream()
                .map(VideoSortTag::getTagName)
                .collect(Collectors.joining(","));
        vmm.setTags(tags);
        return videoMetadataMapper.insert(vmm) > 0;
    }

    @Override
    public VideoInfoVO randomVideoInfo() {
        // todo 需要使用Redis优化,暂时就不加注释了，基本为测试代码
        VideoInfoVO videoInfoVO = new VideoInfoVO();
        List<VideoMetadataModel> videoMetadataModels;
        // 首先查看缓存
        String key = RedisConstant.ALL_VIDEO_KEY;
        if (redisUtils.hasKey(key)){
            videoMetadataModels = (List<VideoMetadataModel>) redisUtils.get(key);
        }else {
            videoMetadataModels= videoMetadataMapper.selectList(null);
            // 添加缓存
            redisUtils.set(key,videoMetadataModels,600);
        }
        Random random = new Random();
        int index= random.nextInt(videoMetadataModels.size());
        VideoMetadataModel videoMetadataModel = videoMetadataModels.get(index);
        // 封装信息
        modelToVO(videoMetadataModel, videoInfoVO);
        return videoInfoVO;
    }

    @Override
    public List<VideoInfoVO> getPhysicalCultureVideo(String tag) {
        LambdaQueryWrapper<VideoMetadataModel> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(true, VideoMetadataModel::getTags, tag);
        wrapper.last("limit 6");
        // todo redis优化
        List<VideoMetadataModel> videoMetadataModels = videoMetadataMapper.selectList(wrapper);
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
        return res;
    }

    @Override
    public VideoInfoVO getVideoById(String videoId) {
        String key = String.format(RedisConstant.VIDEO_INFO_KEY,videoId);
        // 首先查询是否命中缓存
        if (redisUtils.hasKey(key)){
            return (VideoInfoVO) redisUtils.get(key);
        }
        // 查询数据库
        VideoMetadataModel videoMetadataModel = videoMetadataMapper.selectById(videoId);
        VideoInfoVO videoInfoVO = new VideoInfoVO();
        // 封装信息
        modelToVO(videoMetadataModel, videoInfoVO);
        // 加入缓存
        redisUtils.set(key,videoInfoVO,600);
        return videoInfoVO;
    }

    private void modelToVO(VideoMetadataModel videoMetadataModel, VideoInfoVO videoInfoVO) {
        videoInfoVO.setVideoId(String.valueOf(videoMetadataModel.getId()));
        videoInfoVO.setVideoM3U8Url(videoMetadataModel.getFilePath());
        videoInfoVO.setVideoTitle(videoMetadataModel.getTitle());
        videoInfoVO.setPublishTime(videoMetadataModel.getUploadTime());
        Long videoLikeCount = videoLikeService.getVideoLikeCount(Long.valueOf(videoInfoVO.getVideoId()));
        videoInfoVO.setVideLikeCount(videoLikeCount);
        Long videoCollectCount = videoCollectService.getVideoCollectCount(Long.valueOf(videoInfoVO.getVideoId()));
        videoInfoVO.setVideoCollectCount(videoCollectCount);
        Integer commentTotalCount = videoCommentService.getCommentTotalCount(Long.valueOf(videoInfoVO.getVideoId()), null);
        videoInfoVO.setVideoCommentCount(Long.valueOf(commentTotalCount));
        if (videoMetadataModel.getUploaderId() == null)videoMetadataModel.setUploaderId("0");
        UserModel userModel = userFeginApi.getUserById(Long.valueOf(videoMetadataModel.getUploaderId())).getData();
        videoInfoVO.setUser(userModel);
    }
}
