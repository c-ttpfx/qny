package com.qny.video.controller;

import com.qny.common.utils.JwtUtil;
import com.qny.video.domain.dto.VideoCommentDTO;
import com.qny.common.domain.entity.Result;
import com.qny.video.domain.request.VideoCommentRequest;
import com.qny.video.domain.vo.VideoCommentShowVO;
import com.qny.video.domain.vo.VideoCommentVO;
import com.qny.common.exception.VerifyException;
import com.qny.video.service.CommentLikeService;
import com.qny.video.service.VideoCommentService;
import com.qny.common.utils.ValidationUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

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
    @Resource
    private CommentLikeService commentLikeService;

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
        // 获取用户id
        Long userID = Long.valueOf(JwtUtil.getUserID());
        videoCommentDTO.setUserId(userID);
        boolean flag = videoCommentService.save(videoCommentDTO);
        return flag ? Result.ok() : Result.fail();
    }

    /**
     * 获取一级评论
     *
     * @param videoId 视频id
     * @return Result
     */
    @GetMapping("/getRootComment")
    public Result getRootComment(@NotNull(message = "视频id不能为空")
                                 @RequestParam("videoId") Long videoId) {
        VideoCommentVO videoCommentVO = videoCommentService.getRootComment(videoId);
        return Result.ok(videoCommentVO);
    }

    /**
     * 获取2级评论
     *
     * @return Result
     */
    @GetMapping("/getCommentReply")
    public Result getCommentReply(@NotNull(message = "视频id不能为空")
                                  @RequestParam("videoId") Long videoId,
                                  @NotNull(message = "评论id不能为空")
                                  @RequestParam("commentId") Long commentId) {
        List<VideoCommentShowVO> videoCommentShowVOS = videoCommentService.getCommentReply(videoId,commentId);
        return Result.ok(videoCommentShowVOS);
    }

    /**
     * 获取评论点赞次数
     * @param commentId 评论id
     * @return Result
     */
    @GetMapping("/getCommentLikeCount")
    public Result getCommentLikeCount(@RequestParam("commentId") Long commentId){
        Long count = commentLikeService.getCommentLikeCount(commentId);
        return Result.ok(count);
    }

    /**
     * 增加评论点赞数
     * @param commentId 评论id
     * @return Result
     */
    @PutMapping("/addCommentLikeCount")
    public Result addCommentLikeCount(@RequestParam("commentId")
                               @NotNull(message = "评论id不能为空")
                               Long commentId) {
        // 获取用户id
        Long userId = Long.valueOf(JwtUtil.getUserID());
        boolean isOk = commentLikeService.addCommentLikeCount(commentId, userId);
        return Result.ok(isOk);
    }

    /**
     * 减少评论点赞数
     * @param commentId 评论id
     * @return Result
     */
    @PutMapping("/subCommentLikeCount")
    public Result subCommentLikeCount(@RequestParam("commentId")
                                   @NotNull(message = "评论id不能为空")
                                   Long commentId) {
        // 获取用户id
        Long userId = Long.valueOf(JwtUtil.getUserID());
        boolean isOk = commentLikeService.subCommentLikeCount(commentId, userId);
        return Result.ok(isOk);
    }

    /**
     * 当前用户评论是否点赞
     *
     * @param commentId 评论id
     * @return Result
     */
    @GetMapping("/isCommentLike")
    public Result isCommentLike(@RequestParam("commentId")
                         @NotNull(message = "评论id不能为空")
                         @NotNull Long commentId) {
        // 获取用户id
        Long userId = Long.valueOf(JwtUtil.getUserID());
        boolean isLike = commentLikeService.isCommentLike(commentId, userId);
        return Result.ok(isLike);
    }
}
