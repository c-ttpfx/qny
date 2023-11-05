package com.qny.video.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qny.common.utils.RedisUtils;
import com.qny.video.constant.RedisConstant;
import com.qny.video.domain.model.CommentLikeModel;
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
    @Resource
    private RedisUtils redisUtils;
    @Override
    public Long getCommentLikeCount(Long commentId) {
        if (commentId == null){
            return 0L;
        }
        String key = String.format(RedisConstant.COMMENT_LIKE_COUNT_KEY, commentId);
        // 首先查询Redis
        if (redisUtils.hasKey(key)) {
            return redisUtils.getLong(key);
        }
        // 查询数据库
        Long count = commentLikeMapper.selectCount(Wrappers
                .<CommentLikeModel>lambdaQuery()
                .eq(CommentLikeModel::getCommentId, commentId));
        // 将数据存储到Redis
        redisUtils.set(key,count,600);
        return count;
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
        boolean flag=  commentLikeMapper.insert(commentLikeModel) > 0;
        if (flag) {
            // 数据更新删除Redis
            String key = String.format(RedisConstant.COMMENT_LIKE_COUNT_KEY, commentId);
            redisUtils.del(key);
            key = String.format(RedisConstant.USER_COMMENT_LIKE_KEY,userId,commentId);
            redisUtils.del(key);
        }
        return  flag;
    }

    @Override
    public Boolean subCommentLikeCount(Long commentId, Long userId) {
        if (commentId == null || userId == null) {
            return false;
        }
        // 删除记录
        boolean flag=  commentLikeMapper.delete(Wrappers
                .<CommentLikeModel>lambdaQuery()
                .eq(CommentLikeModel::getCommentId, commentId)
                .eq(CommentLikeModel::getUserId, userId)) > 0;
        if (flag) {
            // 数据更新删除Redis
            String key = String.format(RedisConstant.COMMENT_LIKE_COUNT_KEY, commentId);
            redisUtils.del(key);
            key = String.format(RedisConstant.USER_COMMENT_LIKE_KEY,userId,commentId);
            redisUtils.del(key);
        }
        return flag;
    }

    @Override
    public Boolean isCommentLike(Long commentId, Long userId) {
        if (commentId == null || userId == null) {
            return false;
        }
        String key = String.format(RedisConstant.USER_COMMENT_LIKE_KEY, userId,commentId);
        // 首先查询Redis
        if (redisUtils.hasKey(key)) {
            return (Boolean) redisUtils.get(key);
        }
        // 查询数据库
        boolean flag =  commentLikeMapper.selectCount(Wrappers
                .<CommentLikeModel>lambdaQuery()
                .eq(CommentLikeModel::getCommentId, commentId)
                .eq(CommentLikeModel::getUserId, userId)) > 0;
        // 缓存Redis
        redisUtils.set(key,flag,600);
        return flag;
    }
}
