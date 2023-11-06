package com.qny.video.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Knight
 * @since 2023/11/6
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class VideoRankVO {
    private String videoId;
    private String videoTitle;
    private Long videoHots;
}
