package com.qny.video.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 视频下面一级评论VO对象
 * @author ttpfx
 * @since 2023/11/1
 */
@Data
public class VideoCommentShowVO implements Serializable {
    private static final long serialVersionUID = 1234567890L;

    // 评论id
    private Long id;
    // 视频id，不能使用Long，前端js有精度丢失问题
    private String videoId;
    // 用户id
    private Long userId;
    // 评论信息
    private String commentContent;
    // 评论时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date commentTime;
    // 评论地点
    private String commentRegion;
    // 父评论id，也就是回复了谁
    private Long parentId;
    // 顶级父id，最开始在哪个评论下进行评论和回复的
    private Long rootId;
    // 追评的条数
    private Integer replyCount;
    // 点赞次数
    private Integer likeCount;
    // 用户头像
    private String userIcon;
    // 用户名称
    private String username;
}
