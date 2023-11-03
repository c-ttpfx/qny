package com.qny.video.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qny.video.domain.model.VideoLikeModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ttpfx
 * @since 2023/10/29
 */
@Mapper
public interface VideoLikeMapper extends BaseMapper<VideoLikeModel> {
}
