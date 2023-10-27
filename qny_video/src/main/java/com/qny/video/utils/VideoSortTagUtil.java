package com.qny.video.utils;

import com.qny.video.enumeration.VideoSortTag;

/**
 * 视频标签工具类
 * @author ttpfx
 * @since 2023/10/28
 */
public class VideoSortTagUtil {

    /**
     * 通过tag标签返回枚举类
     * @param tag 标签
     * @return VideoSortTag
     */
    public static VideoSortTag get(String tag){
        try {
           return VideoSortTag.getByValue(tag);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
