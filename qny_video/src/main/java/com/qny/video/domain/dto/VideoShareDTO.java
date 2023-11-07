package com.qny.video.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author ttpfx
 * @since 2023/11/7
 */
@Data
public class VideoShareDTO implements Serializable {
    private static final long serialVersionUID = 1234567890L;

    // 视频id
    private Long videoId;
    // 被分享者id
    private Long toUserId;
    // 分享者id
    private Long userId;
    // 分享时间
    private Long shareTime;
}
