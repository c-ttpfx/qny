package com.qny.video.domain.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author ttpfx
 * @since 2023/11/7
 */
@Data
public class VideoShareRequest implements Serializable {
    private static final long serialVersionUID = 1234567890L;

    // 视频id
    @NotNull(message = "视频id不能为空")
    private Long videoId;
    // 被分享者id
    @NotNull(message = "被分享者id不能为空")
    private Long toUserId;
    // 分享者id
    private Long userId;
}
