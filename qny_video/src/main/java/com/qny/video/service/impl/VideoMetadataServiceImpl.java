package com.qny.video.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qny.video.domain.entity.VideoMetadata;
import com.qny.video.mapper.VideoMetadataMapper;
import com.qny.video.service.VideoMetadataService;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author ttpfx
 * @since 2023/10/27
 */
@Service
public class VideoMetadataServiceImpl extends ServiceImpl<VideoMetadataMapper, VideoMetadata> implements VideoMetadataService {

}
