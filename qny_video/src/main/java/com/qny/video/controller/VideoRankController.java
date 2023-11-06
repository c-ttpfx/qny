package com.qny.video.controller;

import com.qny.common.domain.entity.Result;
import com.qny.video.domain.vo.VideoRankVO;
import com.qny.video.service.VideoRankService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 视频热榜控制器
 * @author Knight
 * @since 2023/11/6
 **/

@RestController
@RequestMapping("/videoRank")
public class VideoRankController {
    @Resource
    private VideoRankService videoRankService;

    @RequestMapping("/getVideoRanking")
    public Result<List<VideoRankVO>> getVideoRank() {
        List<VideoRankVO> rank = videoRankService.getVideoRank();
        return Result.ok(rank);
    }

    @RequestMapping("/addVideoRankData")
    public Result<Object> addVideoRankData() {
        ArrayList<Long> list = new ArrayList<>();
        list.add(1717835677724348418L);
        list.add(1717835721911341058L);
        list.add(1717835771882278914L);
        boolean b = videoRankService.setVideoRank(list);
        return Result.ok();
    }

}
