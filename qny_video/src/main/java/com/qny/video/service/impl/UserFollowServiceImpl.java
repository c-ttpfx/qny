package com.qny.video.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qny.common.domain.model.UserModel;
import com.qny.common.utils.RedisUtils;
import com.qny.video.constant.RedisConstant;
import com.qny.video.domain.model.UserFollowModel;
import com.qny.video.domain.vo.UserVO;
import com.qny.video.fegin.UserFeginApi;
import com.qny.video.mapper.UserFollowMapper;
import com.qny.video.service.UserFollowService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ttpfx
 * @since 2023/11/6
 */
@Service
public class UserFollowServiceImpl extends ServiceImpl<UserFollowMapper, UserFollowModel> implements UserFollowService {

    @Resource
    private UserFollowMapper userFollowMapper;
    @Resource
    private UserFeginApi userFeginApi;
    @Resource
    private RedisUtils redisUtils;

    @Override
    public Boolean follow(Long userId, Long followUserId) {
        if (userId == null || followUserId == null) {
            return false;
        }
        UserFollowModel userFollowModel = new UserFollowModel();
        userFollowModel.setUserId(userId);
        userFollowModel.setFollowUserId(followUserId);
        userFollowModel.setFollowDate(new Date().getTime());
        // 保存数据库
        boolean flag = userFollowMapper.insert(userFollowModel) > 0;
        // 刷新缓存
        String key = String.format(RedisConstant.USER_ALL_FOLLOW_KEY, userId);
        redisUtils.del(key);
        return flag;
    }

    @Override
    public Boolean cancelFollow(Long userId, Long followUserId) {
        if (userId == null || followUserId == null) {
            return false;
        }
        boolean flag = userFollowMapper.delete(Wrappers
                .<UserFollowModel>lambdaQuery()
                .eq(UserFollowModel::getUserId, userId)
                .eq(UserFollowModel::getFollowUserId, followUserId)) > 0;
        // 刷新缓存
        String key = String.format(RedisConstant.USER_ALL_FOLLOW_KEY, userId);
        redisUtils.del(key);
        return flag;
    }

    @Override
    public List<UserVO> allFollowInfo(Long userId) {
        if (userId == null) {
            return null;
        }
        String key = String.format(RedisConstant.USER_ALL_FOLLOW_KEY, userId);
        // 先查询是否命中缓存
        if (redisUtils.hasKey(key)) {
            return (List<UserVO>) redisUtils.get(key);
        }
        // 查询数据库
        List<UserFollowModel> userFollowModels = userFollowMapper.selectList(Wrappers
                .<UserFollowModel>lambdaQuery()
                .eq(UserFollowModel::getUserId, userId));
        // 获取所有关注者id
        Long[] followIds = userFollowModels
                .stream()
                .map(UserFollowModel::getFollowUserId).
                toArray(Long[]::new);
        // 远程调用获取用户信息
        List<UserModel> userModels = userFeginApi.getUserByIds(followIds).getData();
        // 将UserModel转换为UserVO
        List<UserVO> userVOS = userModels.stream().map(userModel -> {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(userModel, userVO);
            userVO.setStatus("在线");
            return userVO;
        }).collect(Collectors.toList());
        // 加入缓存
        redisUtils.set(key, userVOS, 600);
        return userVOS;
    }

    @Override
    public List<UserVO> searchFollow(Long userId, String name) {
        List<UserVO> userVOS = allFollowInfo(userId);
        // 通过名称过滤
        return userVOS.stream()
                .filter(userVO -> userVO.getName().contains(name))
                .collect(Collectors.toList());
    }
}
