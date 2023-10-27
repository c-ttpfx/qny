package com.qny.video.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qny.video.domain.dto.VideoMetadataDTO;
import com.qny.video.domain.model.VideoMetadataModel;
import com.qny.video.enumeration.VideoSortTag;
import com.qny.video.mapper.VideoMetadataMapper;
import com.qny.video.service.VideoMetadataService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.stream.Collectors;

/**
 * @author ttpfx
 * @since 2023/10/27
 */
@Service
public class VideoMetadataServiceImpl extends ServiceImpl<VideoMetadataMapper, VideoMetadataModel> implements VideoMetadataService {

    @Resource
    private VideoMetadataMapper videoMetadataMapper;

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
}
