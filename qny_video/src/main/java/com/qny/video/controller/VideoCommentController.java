package com.qny.video.controller;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.qny.video.domain.dto.VideoCommentDTO;
import com.qny.video.domain.entity.Result;
import com.qny.video.domain.request.VideoCommentRequest;
import com.qny.video.domain.vo.VideoCommentVO;
import com.qny.video.exception.VerifyException;
import com.qny.video.service.VideoCommentService;
import com.qny.video.utils.ValidationUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 用于提供视频评论接口
 *
 * @author ttpfx
 * @since 2023/10/29
 */
@RestController
@RequestMapping("/comment")
public class VideoCommentController {

    @Resource
    private VideoCommentService videoCommentService;


    /**
     * 视频评论接口
     *
     * @param videoCommentRequest VideoCommentRequest
     * @param bindingResult       BindingResult
     * @return Result
     * @throws VerifyException 参数校验异常
     */
    @PostMapping("/save")
    public Result save(@RequestBody @Valid VideoCommentRequest videoCommentRequest, BindingResult bindingResult) throws VerifyException {
        ValidationUtil.check(bindingResult);
        VideoCommentDTO videoCommentDTO = new VideoCommentDTO();
        BeanUtils.copyProperties(videoCommentRequest, videoCommentDTO);
        videoCommentDTO.setCommentTime(System.currentTimeMillis());
        // todo 当前全部都使用重庆
        videoCommentDTO.setCommentRegion("重庆");
        videoCommentDTO.setUserId(111L);
        boolean flag = videoCommentService.save(videoCommentDTO);
        return flag ? Result.ok() : Result.fail();
    }

    @GetMapping("/getRootComment")
    public Result getRootComment(@NotNull(message = "视频id不能为空")
                          @RequestParam("videoId") Long videoId) {
        VideoCommentVO videoCommentVO = videoCommentService.getRootComment(videoId);
        return Result.ok(videoCommentVO);
    }
}
