package com.qny.video.service.impl;

import com.qny.common.constant.RedisConstant;
import com.qny.common.exception.GeneralException;
import com.qny.common.utils.RedisUtils;
import com.qny.video.domain.model.VideoMetadataModel;
import com.qny.video.domain.vo.VideoRankVO;
import com.qny.video.service.VideoLikeService;
import com.qny.video.service.VideoMetadataService;
import com.qny.video.service.VideoRankService;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Knight
 * @since 2023/11/6
 **/
@Service
public class VideoRankServiceImpl implements VideoRankService {

    @Resource
    private RedisUtils redisUtils;
    @Resource
    private VideoLikeService videoLikeService;
    @Resource
    private VideoMetadataService videoMetadataService;

    @Override
    public List<VideoRankVO> getVideoRank() {
        List<VideoRankVO> res = new ArrayList<>();
        Set<ZSetOperations.TypedTuple<Object>> dataFromZSet =
                redisUtils.getDataFromZSet(RedisConstant.VIDEO_RANK_KEY, 0, 10);
        for (ZSetOperations.TypedTuple<Object> item : dataFromZSet) {
            Double score = item.getScore();
            VideoMetadataModel value = (VideoMetadataModel)item.getValue();
            if (score == null || value == null) throw new GeneralException("Redis排行数据错误");
            VideoRankVO videoRankVO = VideoRankVO.builder().videoId(value.getId().toString()).videoTitle(value.getTitle())
                    .videoHots(score.longValue()).build();
            res.add(videoRankVO);
        }
        return res;
    }

    @Override
    public boolean setVideoRank(List<Long> videoIds) {
        for (long videoId : videoIds) {
            VideoMetadataModel metadataModel = videoMetadataService.getById(videoId);
            Long likeCount = videoLikeService.getVideoLikeCount(metadataModel.getId());
            redisUtils.addZSet(RedisConstant.VIDEO_RANK_KEY, metadataModel, likeCount);
        }
        return true;
    }
}
