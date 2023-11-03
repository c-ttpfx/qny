package com.qny.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qny.video.domain.model.CommentLikeModel;

/**
 * @author ttpfx
 * @since 2023/11/3
 */
public interface CommentLikeService extends IService<CommentLikeModel> {
    /**
     * 获取评论点赞数
     * @param commentId 评论id
     * @return 点赞数
     */
    Long getCommentLikeCount(Long commentId);

    /**
     * 增加评论点赞数
     * @param commentId 评论id
     * @param userId 用户id
     * @return true/false
     */
    Boolean addCommentLikeCount(Long commentId, Long userId);

    /**
     * 减少评论点赞数
     * @param commentId 评论id
     * @param userId 用户id
     * @return true/false
     */
    Boolean subCommentLikeCount(Long commentId, Long userId);

    /**
     * 用户是否点赞评论
     * @param commentId 评论id
     * @param userId 用户id
     * @return true/false
     */
    Boolean isCommentLike(Long commentId, Long userId);
}
