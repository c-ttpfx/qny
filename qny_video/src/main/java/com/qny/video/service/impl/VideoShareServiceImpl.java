package com.qny.video.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qny.video.domain.model.VideoCollectModel;
import com.qny.video.domain.model.VideoShareModel;
import com.qny.video.mapper.VideoCollectMapper;
import com.qny.video.mapper.VideoShareMapper;
import com.qny.video.service.VideoCollectService;
import com.qny.video.service.VideoShareService;
import org.springframework.stereotype.Service;

/**
 * @author ttpfx
 * @since 2023/10/29
 */
@Service
public class VideoShareServiceImpl extends ServiceImpl<VideoShareMapper, VideoShareModel> implements VideoShareService {
}
