package com.qny.video.domain.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ttpfx
 * @since 2023/11/3
 */
@Data
@TableName("comment_like")
public class CommentLikeModel implements Serializable {
    private static final long serialVersionUID = 1234567890L;

    // 用户id
    private Long userId;
    // 评论id
    private Long commentId;
    // 点赞时间
    private Long time;
}
