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
import com.qny.video.service.VideoCommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
        );
        // 获取评论用户的id数组
        Long[] userIdsArray = rootCommentList
                .stream()
                .map(VideoCommentModel::getUserId).toArray(Long[]::new);
        // 通过id集合获取对应的用户集合
        List<UserModel> userModelList = userFeginApi.getUserByIds(userIdsArray).getData();
        if (userModelList == null)userModelList = new ArrayList<>();
        // 生成id->User的Map
        Map<Long, UserModel> userMap = userModelList
                .stream()
                .collect(Collectors.toMap(UserModel::getUserId, x -> x));

        // 将Model转换成VO
        List<VideoCommentShowVO> rootCommentVo = rootCommentList.stream().map(model -> {
            VideoCommentShowVO vo = new VideoCommentShowVO();
            BeanUtils.copyProperties(model, vo);
            // todo 先给个默认值0
            vo.setLikeCount(0);
            // 追评的条数
            Integer replyCount = getCommentTotalCount(videoId, vo.getId());
            vo.setReplyCount(replyCount);
            // 设置头像
            UserModel user = userMap.get(model.getUserId());
            vo.setUserIcon(user==null?"http://localhost:10002/images/路飞头像.png":user.getIcon());
            // 设置时间
            vo.setCommentTime(new Date(model.getCommentTime()));
            // 设置视频id
            vo.setVideoId(String.valueOf(model.getVideoId()));
            // 设置用户名称
            vo.setUsername(user==null?"未知":user.getName());
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
                .eq(commentId != null, VideoCommentModel::getId, commentId));
        return Math.toIntExact(count);
    }
}
