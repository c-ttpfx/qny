package com.qny.video.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ttpfx
 * @since 2023/11/1
 */
@Data
public class VideoCommentDTO implements Serializable {
    private static final long serialVersionUID = 1234567890L;

    // 视频id
    private Long videoId;
    // 评论内容
    private String commentContent;
    // 顶级评论id
    private Long rootId;
    // 父评论id
    private Long parentId;
    // 用户id
    private Long userId;
    // 评论时间
    private Long commentTime;
    // 评论地点
    private String commentRegion;
}
