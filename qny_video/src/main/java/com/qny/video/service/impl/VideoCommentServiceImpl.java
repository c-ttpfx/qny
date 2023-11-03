package com.qny.video.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qny.video.domain.dto.VideoCommentDTO;
import com.qny.video.domain.model.UserModel;
import com.qny.video.domain.model.VideoCommentModel;
import com.qny.video.domain.vo.VideoCommentShowVO;
import com.qny.video.domain.vo.VideoCommentVO;
import com.qny.video.fegin.UserFeginApi;
import com.qny.video.fegin.excp.UserFeginServiceFallBackFactory;
import com.qny.video.mapper.VideoCommentMapper;
import com.qny.video.service.CommentLikeService;
import com.qny.video.service.VideoCommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ttpfx
 * @since 2023/10/29
 */
@Service
public class VideoCommentServiceImpl extends ServiceImpl<VideoCommentMapper, VideoCommentModel> implements VideoCommentService {
    @Resource
    private VideoCommentMapper videoCommentMapper;
    @Resource
    private UserFeginApi userFeginApi;
    @Resource
    private CommentLikeService commentLikeService;

    @Override
    public boolean save(VideoCommentDTO videoCommentDTO) {
        VideoCommentModel videoCommentModel = new VideoCommentModel();
        BeanUtils.copyProperties(videoCommentDTO, videoCommentModel);
        // 插入数据
        return videoCommentMapper.insert(videoCommentModel) > 0;
    }

    @Override
    public VideoCommentVO getRootComment(Long videoId) {
        if (videoId == null) {
            return null;
        }
        // 远程调用获取User信息
        UserModel userModel = userFeginApi.getUserById(1L).getData();
        if (userModel == null) {
            userModel = new UserModel();
        }
        VideoCommentVO videoCommentVO = new VideoCommentVO();
        // 设置视频id
        videoCommentVO.setVideoId(String.valueOf(videoId));
        // 设置评论总条数
        Integer count = getCommentTotalCount(videoId, null);
        videoCommentVO.setCommentTotalCount(count);
        // 获取一级评论
        List<VideoCommentModel> rootCommentList = videoCommentMapper.selectList(Wrappers
                .<VideoCommentModel>lambdaQuery()
                .eq(VideoCommentModel::getVideoId, videoId)
                .isNull(VideoCommentModel::getRootId)
                .isNull(VideoCommentModel::getParentId)
        );
        // 获取评论用户的id数组
        Long[] userIdsArray = rootCommentList
                .stream()
                .map(VideoCommentModel::getUserId).toArray(Long[]::new);
        // 通过id集合获取对应的用户集合
        List<UserModel> userModelList = userFeginApi.getUserByIds(userIdsArray).getData();
        if (userModelList == null) userModelList = new ArrayList<>();
        // 生成id->User的Map
        Map<Long, UserModel> userMap = userModelList
                .stream()
                .collect(Collectors.toMap(UserModel::getUserId, x -> x));

        // 将Model转换成VO
        List<VideoCommentShowVO> rootCommentVo = rootCommentList.stream().map(model -> {
            VideoCommentShowVO vo = new VideoCommentShowVO();
            BeanUtils.copyProperties(model, vo);
            vo.setUserId(String.valueOf(model.getUserId()));
            vo.setId(String.valueOf(model.getId()));
            // 评论点赞数
            Long commentLikeCount = commentLikeService.getCommentLikeCount(model.getId());
            vo.setLikeCount(Math.toIntExact(commentLikeCount));
            // 设置评论id
            vo.setId(String.valueOf(model.getId()));
            // 追评的条数
            Integer replyCount = getCommentTotalCount(videoId, model.getId());
            vo.setReplyCount(replyCount);
            // 设置头像
            UserModel user = userMap.get(model.getUserId());
            vo.setUserIcon(user == null ? "http://localhost:10002/images/路飞头像.png" : user.getIcon());
            // 设置时间
            vo.setCommentTime(new Date(model.getCommentTime()));
            // 设置视频id
            vo.setVideoId(String.valueOf(model.getVideoId()));
            // 设置用户名称
            vo.setUsername(user == null ? "匿名用户" : user.getName());
            // 判断指定用户是否对评论进行点赞
            Long userId = 111L;
            Boolean isLike = commentLikeService.isCommentLike(model.getId(), userId);
            vo.setIsLike(isLike);
            return vo;
        }).collect(Collectors.toList());
        videoCommentVO.setCommentShowList(rootCommentVo);
        return videoCommentVO;
    }

    @Override
    public Integer getCommentTotalCount(Long videoId, Long commentId) {
        if (videoId == null) {
            return 0;
        }
        // 查询数据库
        long count = count(Wrappers
                .<VideoCommentModel>lambdaQuery()
                .eq(VideoCommentModel::getVideoId, videoId)
                .and(commentId != null, wrapper -> wrapper
                        .eq(VideoCommentModel::getParentId, commentId)
                        .or()
                        .eq(VideoCommentModel::getRootId, commentId)));
        return Math.toIntExact(count);
    }

    @Override
    public List<VideoCommentShowVO> getCommentReply(Long videoId, Long commentId) {
        // 获取非一级评论
        List<VideoCommentModel> rootCommentList = videoCommentMapper.selectList(Wrappers
                .<VideoCommentModel>lambdaQuery()
                .eq(VideoCommentModel::getVideoId, videoId)
                .and(wrapper -> {
                    wrapper
                            .eq(VideoCommentModel::getParentId, commentId)
                            .or()
                            .eq(VideoCommentModel::getRootId, commentId);
                }));
        // 查询顶级评论
        VideoCommentModel videoCommentModel = videoCommentMapper.selectById(commentId);
        rootCommentList.add(videoCommentModel);
        // 评论id->用户id
        Map<Long, Long> commentToUserMap = rootCommentList
                .stream()
                .collect(Collectors.toMap(VideoCommentModel::getId, VideoCommentModel::getUserId));

        // 获取评论用户的id数组
        Long[] userIdsArray = rootCommentList
                .stream()
                .map(VideoCommentModel::getUserId).toArray(Long[]::new);
        // 通过id集合获取对应的用户集合
        List<UserModel> userModelList = userFeginApi.getUserByIds(userIdsArray).getData();
        if (userModelList == null) userModelList = new ArrayList<>();
        // 生成id->User的Map
        Map<Long, UserModel> userMap = userModelList
                .stream()
                .collect(Collectors.toMap(UserModel::getUserId, x -> x));
        // 将Model转换成VO
        List<VideoCommentShowVO> rootCommentVo = rootCommentList
                .stream()
                .filter(model-> model != videoCommentModel)
                .map(model -> {
                    VideoCommentShowVO vo = new VideoCommentShowVO();
                    BeanUtils.copyProperties(model, vo);
                    vo.setUserId(String.valueOf(model.getUserId()));
                    vo.setId(String.valueOf(model.getId()));
                    if (model.getRootId()!=null) {
                        vo.setRootId(String.valueOf(model.getRootId()));
                    }
                    if (model.getParentId() != null) {
                        vo.setParentId(String.valueOf(model.getParentId()));
                    }
                    // 评论点赞数
                    Long commentLikeCount = commentLikeService.getCommentLikeCount(model.getId());
                    vo.setLikeCount(Math.toIntExact(commentLikeCount));
                    // 设置头像
                    UserModel user = userMap.get(model.getUserId());
                    vo.setUserIcon(user == null ? "http://localhost:10002/images/路飞头像.png" : user.getIcon());
                    // 设置时间
                    vo.setCommentTime(new Date(model.getCommentTime()));
                    // 设置视频id
                    vo.setVideoId(String.valueOf(model.getVideoId()));
                    // 设置用户名称
                    vo.setUsername(user == null ? "未知" : user.getName());
                    if (vo.getParentId() != null && vo.getRootId() != null) {
                        UserModel userModel = userMap.get(commentToUserMap.get(model.getParentId()));
                        vo.setUsername(vo.getUsername() + " => " + (userModel == null ? "匿名用户" : userModel.getName()));
                    }
                    // 判断指定用户是否对评论进行点赞
                    Long userId = 111L;
                    Boolean isLike = commentLikeService.isCommentLike(model.getId(), userId);
                    vo.setIsLike(isLike);
                    return vo;
                }).collect(Collectors.toList());
        return rootCommentVo;
    }
}
