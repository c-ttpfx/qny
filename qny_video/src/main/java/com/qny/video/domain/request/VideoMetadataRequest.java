package com.qny.video.domain.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.qny.video.converter.VideoSortTagListDeserializer;
import com.qny.video.enumeration.VideoSortTag;
import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author ttpfx
 * @since 2023/10/27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoMetadataRequest implements Serializable {
    private static final long serialVersionUID = 1234567890L;

    @NotEmpty(message = "视频标题不能为空")
    @JsonProperty("title")
    private String title;  // 视频标题
    @NotEmpty(message = "视频描述不能为空")
    @JsonProperty("description")
    private String description;  // 视频描述
    // @javax.validation.constraints.NotNull(message = "视频上传者id不能为空")
    private String uploaderId;  // 上传者ID
    @NotNull
    @JsonProperty("uploadTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Date uploadTime;  // 上传时间
    @Min(1)
    private Long duration;  // 视频时长
    @NotEmpty(message = "视频路径不能为空")
    private String filePath;  // 文件路径（在服务器上的存储路径）
    @NotEmpty(message = "视频格式不能为空")
    private String format;  // 视频格式（例如：MP4, AVI, MKV等）
    private String resolution;  // 视频分辨率（例如：1920x1080）
    @Min(1)
    private Long fileSize;  // 文件大小（单位：字节）
    @JsonDeserialize(using = VideoSortTagListDeserializer.class)
    private List<VideoSortTag> tags;  // 标签(以逗号分隔的标签列表)
}
