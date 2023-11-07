package com.qny.video.controller;

import com.qny.common.domain.entity.Result;
import com.qny.common.utils.JwtUtil;
import com.qny.video.domain.dto.VideoShareDTO;
import com.qny.video.domain.request.VideoShareRequest;
import com.qny.video.domain.vo.VideoShareVO;
import com.qny.video.service.VideoShareService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 用于提供视频分享接口
 * @author ttpfx
 * @since 2023/10/29
 */
@RestController
@RequestMapping("/videoShare")
public class VideoShareController {

    @Resource
    private VideoShareService videoShareService;

    /**
     * 分享视频
     * @param videoShareRequest VideoShareRequest
     * @return Result
     */
    @PostMapping("/videoShare")
    public Result share(@RequestBody @Valid VideoShareRequest videoShareRequest){
        VideoShareDTO videoShareDTO = new VideoShareDTO();
        BeanUtils.copyProperties(videoShareRequest,videoShareDTO);
        String userID = JwtUtil.getUserID();
        // 封装用户id
        videoShareDTO.setUserId(Long.valueOf(userID));
        // 设置时间
        videoShareDTO.setShareTime(System.currentTimeMillis());
        Boolean flag = videoShareService.share(videoShareDTO);
        return Result.ok(flag);
    }

    /**
     * 获取所有的分享视频
     * @param videoId 视频id
     * @return Result
     */
    @GetMapping("/getAllShareVideo")
    public Result getAllShareVideo(){
        Long userId = Long.valueOf(JwtUtil.getUserID());
        List<VideoShareVO> videoShareVoList = videoShareService.getAllShareVideo(userId);
        return Result.ok(videoShareVoList);
    }
}
