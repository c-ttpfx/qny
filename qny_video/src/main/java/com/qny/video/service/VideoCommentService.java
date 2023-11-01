package com.qny.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qny.video.domain.dto.VideoCommentDTO;
import com.qny.video.domain.model.VideoCommentModel;
import com.qny.video.domain.vo.VideoCommentShowVO;
import com.qny.video.domain.vo.VideoCommentVO;

import java.util.List;

/**
 * @author ttpfx
 * @since 2023/10/29
 */
public interface VideoCommentService extends IService<VideoCommentModel> {
    /**
     * 保存信息
     * @param videoCommentDTO VideoCommentDTO
     * @return true/false
     */
    boolean save(VideoCommentDTO videoCommentDTO);

    /**
     * 获取视频一级评论信息
     * @param videoId 视频id
     * @return VideoCommentVO
     */
    VideoCommentVO getRootComment(Long videoId);

    /**
     * 查询评论总条数，commentId为空就是查询所有，不为空就是查询对应评论的回复评论数
     * @param videoId 视频id
     * @param commentId 一级评论id
     * @return 评论总条数
     */
    Integer getCommentTotalCount(Long videoId,Long commentId);


    /**
     * 查询视频下某条评论的回复
     * @param videoId 视频id
     * @param commentId 评论id
     * @return List<VideoCommentShowVO>
     */
    List<VideoCommentShowVO> getCommentReply(Long videoId, Long commentId);
}
