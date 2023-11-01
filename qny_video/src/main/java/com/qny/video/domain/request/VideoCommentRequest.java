package com.qny.video.domain.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author ttpfx
 * @since 2023/11/1
 */
@Data
public class VideoCommentRequest implements Serializable {
    private static final long serialVersionUID = 1234567890L;

    // 视频id
    @NotNull(message = "视频id不能为空")
    private Long videoId;

    // 评论内容
    @NotEmpty(message = "评论内容不能为空")
    private String commentContent;

    // 顶级评论id
    private Long rootId;

    // 父评论id
    private Long parentId;
}
