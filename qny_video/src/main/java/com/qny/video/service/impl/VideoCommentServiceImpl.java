package com.qny.video.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qny.video.domain.model.VideoCommentModel;
import com.qny.video.mapper.VideoCommentMapper;
import com.qny.video.service.VideoCommentService;
import org.springframework.stereotype.Service;

/**
 * @author ttpfx
 * @since 2023/10/29
 */
@Service
public class VideoCommentServiceImpl extends ServiceImpl<VideoCommentMapper, VideoCommentModel> implements VideoCommentService {
}
