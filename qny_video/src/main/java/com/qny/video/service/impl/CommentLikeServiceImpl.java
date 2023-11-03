package com.qny.video.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qny.video.domain.model.CommentLikeModel;
import com.qny.video.domain.model.VideoLikeModel;
import com.qny.video.mapper.CommentLikeMapper;
import com.qny.video.service.CommentLikeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author ttpfx
 * @since 2023/11/3
 */
@Service
public class CommentLikeServiceImpl extends ServiceImpl<CommentLikeMapper, CommentLikeModel> implements CommentLikeService {
    @Resource
    private CommentLikeMapper commentLikeMapper;
    @Override
    public Long getCommentLikeCount(Long commentId) {
        if (commentId == null){
            return 0L;
        }
        // 查询数据库
        return commentLikeMapper.selectCount(Wrappers
                .<CommentLikeModel>lambdaQuery()
                .eq(CommentLikeModel::getCommentId,commentId));
    }

    @Override
    public Boolean addCommentLikeCount(Long commentId, Long userId) {
        if (commentId == null || userId == null) {
            return false;
        }
        CommentLikeModel commentLikeModel = new CommentLikeModel();
        commentLikeModel.setCommentId(commentId);
        commentLikeModel.setUserId(userId);
        commentLikeModel.setTime(new Date().getTime());
        // 保存数据
        return commentLikeMapper.insert(commentLikeModel) > 0;
    }

    @Override
    public Boolean subCommentLikeCount(Long commentId, Long userId) {
        if (commentId == null || userId == null) {
            return false;
        }
        // 删除记录
        return commentLikeMapper.delete(Wrappers
                .<CommentLikeModel>lambdaQuery()
                .eq(CommentLikeModel::getCommentId, commentId)
                .eq(CommentLikeModel::getUserId, userId)) > 0;
    }

    @Override
    public Boolean isCommentLike(Long commentId, Long userId) {
        if (commentId == null || userId == null) {
            return false;
        }
        // 查询数据库
        return commentLikeMapper.selectCount(Wrappers
                .<CommentLikeModel>lambdaQuery()
                .eq(CommentLikeModel::getCommentId, commentId)
                .eq(CommentLikeModel::getUserId, userId)) > 0;
    }
}
