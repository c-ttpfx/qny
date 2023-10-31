package com.qny.video.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用于返回前端，里面含有视频评论区各种信息
 * @author ttpfx
 * @since 2023/11/1
 */
@Data
public class VideoCommentVO implements Serializable {
    private static final long serialVersionUID = 1234567890L;
    // 视频id，不能使用Long，前端js有精度丢失问题
    private String videoId;
    // 评论总数
    private Integer commentTotalCount;
    // 顶级评论-一级评论-也就不是追评的
    private List<VideoCommentShowVO> commentShowList;
}
