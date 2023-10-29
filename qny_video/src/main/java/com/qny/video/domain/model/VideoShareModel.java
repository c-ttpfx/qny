package com.qny.video.domain.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 视频分享表
 * @author ttpfx
 * @since 2023/10/29
 */
@Data
@TableName("video_user_share")
public class VideoShareModel implements Serializable {
    private static final long serialVersionUID = 1234567890L;
    // 视频id
    private Long videoId;
    // 分享者id
    private Long userId;
    // 被分享者id
    private Long toUserId;
    // 是否已读，0代表未读，1代表已读
    private Byte hasRead;
    // 分享时间
    private Long shareTime;
}
