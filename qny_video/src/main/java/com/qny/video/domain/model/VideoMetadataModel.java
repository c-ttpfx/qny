package com.qny.video.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ttpfx
 * @since 2023/10/27
 */
@Data
@TableName("video_metadata")
public class VideoMetadataModel implements Serializable {
    private static final long serialVersionUID = 1234567890L;

    @TableId(type = IdType.AUTO)
    private Long id;  // 主键ID
    private String title;  // 视频标题
    private String description;  // 视频描述
    private String uploaderId;  // 上传者ID
    private Date uploadTime;  // 上传时间
    private Long duration;  // 视频时长
    private String format;  // 视频格式（例如：MP4, AVI, MKV等）
    private String resolution;  // 视频分辨率（例如：1920x1080）
    private Long fileSize;  // 文件大小（单位：字节）
    private String filePath;  // 文件路径（在服务器上的存储路径）
    private String tags;  // 标签（以逗号分隔的标签列表）
    private String thumbnailPath;  // 缩略图路径（在服务器上的存储路径）
}
