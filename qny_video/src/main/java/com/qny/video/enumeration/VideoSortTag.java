package com.qny.video.enumeration;

import java.util.HashMap;
import java.util.Map;

/**
 * 视频分类标签枚举类，包含了不同类型的视频分类标签。
 *
 * @author ttpfx
 * @since 2023/10/27
 */
public enum VideoSortTag {

    /**
     * 动漫类视频标签
     */
    ANIMATION("动漫"),

    /**
     * 体育类视频标签
     */
    SPORTS("体育"),

    /**
     * 美妆类视频标签
     */
    BEAUTY("美妆"),

    /**
     * 测评类视频标签
     */
    REVIEW("测评"),

    /**
     * 旅游类视频标签
     */
    TRAVEL("旅游"),

    /**
     * 科技类视频标签
     */
    TECHNOLOGY("科技"),

    /**
     * 教育类视频标签
     */
    EDUCATION("教育"),

    /**
     * 电影类视频标签
     */
    MOVIES("电影"),

    /**
     * 音乐类视频标签
     */
    MUSIC("音乐"),

    /**
     * 新闻类视频标签
     */
    NEWS("新闻"),

    /**
     * 生活类视频标签
     */
    LIFESTYLE("生活"),

    /**
     * 娱乐类视频标签
     */
    ENTERTAINMENT("娱乐"),

    /**
     * 游戏类视频标签
     */
    GAMING("游戏"),

    /**
     * 健康类视频标签
     */
    HEALTH("健康"),

    /**
     * 食品类视频标签
     */
    FOOD("食品"),

    /**
     * 宠物类视频标签
     */
    PETS("宠物"),

    /**
     * 时尚类视频标签
     */
    FASHION("时尚"),

    /**
     * 艺术类视频标签
     */
    ART("艺术"),

    /**
     * 书籍类视频标签
     */
    BOOKS("书籍"),

    /**
     * 车辆类视频标签
     */
    VEHICLES("车辆"),

    /**
     * 科学类视频标签
     */
    SCIENCE("科学");

    /**
     * 标签名称
     */
    private final String tagName;

    /**
     * 构造方法，初始化标签名称。
     *
     * @param tagName 标签名称
     */
    VideoSortTag(String tagName) {
        this.tagName = tagName;
    }

    /**
     * 获取标签名称的方法。
     *
     * @return 标签名称
     */
    public String getTagName() {
        return tagName;
    }

    // 存储枚举值和枚举实例的映射
    private static final Map<String, VideoSortTag> VALUE_MAP = new HashMap<>();

    // 静态代码块，初始化VALUE_MAP
    static {
        for (VideoSortTag tag : values()) {
            VALUE_MAP.put(tag.tagName, tag);
        }
    }

    // 通过值获取枚举实例的静态方法
    public static VideoSortTag getByValue(String value) {
        VideoSortTag tag = VALUE_MAP.get(value);
        if (tag == null) {
            // 如果没有找到匹配的枚举实例，抛出一个异常
            throw new IllegalArgumentException("未知枚举值: " + value);
        }
        return tag;
    }
}

