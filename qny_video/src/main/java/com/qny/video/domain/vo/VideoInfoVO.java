package com.qny.video.domain.vo;

import lombok.Data;

/**
 * 用于返回前端，里面含有视频各种信息
 * @author ttpfx
 * @since 2023/10/29
 */
@Data
public class VideoInfoVO {
    // 视频id，不能使用Long，前端js有精度丢失问题
    private String videoId;
    // 视频m3u8文件地址
    private String videoM3U8Url;
    // 视频点赞数
    private Long videLikeCount;
    // 视频评论数
    private Long videoCommentCount;
    // 视频分享数
    private Long videoShareCount;
    // 视频收藏数
    private Long videoCollectCount;
}