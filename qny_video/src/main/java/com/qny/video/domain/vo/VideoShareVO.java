package com.qny.video.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qny.common.domain.model.UserModel;
import lombok.Data;

import java.util.Date;

/**
 * @author ttpfx
 * @since 2023/11/7
 */
@Data
public class VideoShareVO {

    // 视频id
    private String videoId;
    // 分享者
    private UserModel user;
    // 分享者id
    private String userId;
    // 被分享者id
    private String toUserId;
    // 是否已读，0代表未读，1代表已读
    private Byte hasRead;
    // 分享时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Date shareTime;
    // 视频封面url
    private String videoCover;
}
