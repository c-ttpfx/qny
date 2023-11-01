package com.qny.video.domain.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 视频评论表
 * @author ttpfx
 * @since 2023/10/29
 */
@Data
@TableName("video_user_comment")
public class VideoCommentModel implements Serializable {
    private static final long serialVersionUID = 1234567890L;
    // 评论id
    @TableId
    private Long id;
    // 视频id
    private Long videoId;
    // 用户id
    private Long userId;
    // 评论信息
    private String commentContent;
    // 评论时间
    private Long commentTime;
    // 评论地点
    private String commentRegion;
    // 父评论id，也就是回复了谁
    private Long parentId;
    // 顶级父id，最开始在哪个评论下进行评论和回复的
    private Long rootId;


}
