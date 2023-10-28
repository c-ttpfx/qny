package com.qny.video.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qny.video.domain.model.VideoCollectModel;
import com.qny.video.mapper.VideoCollectMapper;
import com.qny.video.service.VideoCollectService;
import org.springframework.stereotype.Service;

/**
 * @author ttpfx
 * @since 2023/10/29
 */
@Service
public class VideoCollectServiceImpl extends ServiceImpl<VideoCollectMapper, VideoCollectModel> implements VideoCollectService {
}
