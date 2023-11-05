package com.qny.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qny.common.domain.entity.Result;
import com.qny.common.domain.model.UserModel;
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

    @Override
    public boolean save(VideoMetadataDTO vmd) {
        VideoMetadataModel vmm = new VideoMetadataModel();
        // BeanUtils.copyProperties(vmd, vmm, "tags");
        BeanUtils.copyProperties(vmd, vmm);
        // 设置缩略图
        vmm.setThumbnailPath("");
        // // 标志转换
        // String tags = vmd.getTags().stream()
        //         .map(VideoSortTag::getTagName)
        //         .collect(Collectors.joining(","));
        // vmm.setTags(tags);
        return videoMetadataMapper.insert(vmm) > 0;
    }

    @Override
    public VideoInfoVO randomVideoInfo() {
        // todo 需要使用Redis优化,暂时就不加注释了，基本为测试代码
        VideoInfoVO videoInfoVO = new VideoInfoVO();
        List<VideoMetadataModel> videoMetadataModels = videoMetadataMapper.selectList(null);
        Random random = new Random();
        int index= random.nextInt(videoMetadataModels.size());
        VideoMetadataModel videoMetadataModel = videoMetadataModels.get(index);
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
        return videoInfoVO;
    }

    @Override
    public List<VideoInfoVO> getPhysicalCultureVideo(String tag) {
        LambdaQueryWrapper<VideoMetadataModel> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(true, VideoMetadataModel::getTags, tag);
        wrapper.last("limit 10");
        // todo redis优化
        List<VideoMetadataModel> videoMetadataModels = videoMetadataMapper.selectList(wrapper);
        List<VideoInfoVO> res = videoMetadataModels.stream().map(metadata -> {
            VideoInfoVO videoInfoVO = new VideoInfoVO();
            videoInfoVO.setVideoId(metadata.getId().toString());
            videoInfoVO.setVideoM3U8Url(metadata.getFilePath());
            videoInfoVO.setVideoTitle(metadata.getTitle());
            UserModel user = userFeginApi.getUserById(Long.parseLong(metadata.getUploaderId())).getData();
            videoInfoVO.setVideoAuthor(user.getName());
            videoInfoVO.setPublishTime(metadata.getUploadTime());
            return videoInfoVO;
        }).collect(Collectors.toList());
        return res;
    }
}
