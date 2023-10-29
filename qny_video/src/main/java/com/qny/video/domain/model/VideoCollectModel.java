package com.qny.video.domain.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 视频收藏表
 * @author ttpfx
 * @since 2023/10/29
 */
@Data
@TableName("video_user_collect")
public class VideoCollectModel implements Serializable {
    private static final long serialVersionUID = 1234567890L;

    // 视频id
    private Long videoId;
    // 用户id
    private Long userId;
    // 收藏时间
    private Long collectTime;
}
