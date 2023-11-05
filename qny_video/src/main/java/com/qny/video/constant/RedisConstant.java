package com.qny.video.constant;

/**
 * @author ttpfx
 * @since 2023/11/5
 */

public interface RedisConstant {
    /** 评论点赞数KEY */
    public static String COMMENT_LIKE_COUNT_KEY = "comment_like_count_key-{%s}";
    /** 用户是否点赞评论KEY */
    public static String USER_COMMENT_LIKE_KEY = "user_comment_like_key-{%s}-{%s}";

    /** 视频收藏数KEY */
    public static String VIDEO_COLLECT_COUNT_KEY = "video_collect_count_key-{%s}";
    /** 用户是否收藏视频 */
    public static String USER_VIDEO_COLLECT_KEY = "user_video_collect_key-{%s}-{%s}";

    /** 视频点赞数KEY */
    public static String VIDEO_LIKE_COUNT_KEY = "video_like_count_key-{%s}";
    /** 用户是否点赞视频KEY */
    public static String USER_VIDEO_LIKE_KEY = "video_like_count_key-{%s}-{%s}";

    /** 所有视频KEY */
    public static String ALL_VIDEO_KEY = "all_video_key";
}
