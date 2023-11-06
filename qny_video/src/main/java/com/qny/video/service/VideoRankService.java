package com.qny.video.service;

import com.qny.video.domain.vo.VideoRankVO;

import java.util.List;

public interface VideoRankService {

    /**
     * 获取视频排行榜
     * @return
     */
    List<VideoRankVO> getVideoRank();

    boolean setVideoRank(List<Long> videoRank);
}
