package com.qny.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qny.video.domain.dto.VideoMetadataDTO;
import com.qny.video.domain.model.VideoMetadataModel;

/**
 * @author ttpfx
 * @since 2023/10/27
 */
public interface VideoMetadataService extends IService<VideoMetadataModel> {

    /**
     * 往数据库插入一条记录
     * @param vmd VideoMetadataDTO
     * @return true/false
     */
    boolean save(VideoMetadataDTO vmd);
}
