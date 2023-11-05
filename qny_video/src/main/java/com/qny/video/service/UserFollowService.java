package com.qny.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qny.common.domain.model.UserModel;
import com.qny.video.domain.model.UserFollowModel;
import com.qny.video.domain.vo.UserVO;

import java.util.List;

/**
 * @author ttpfx
 * @since 2023/11/6
 */
public interface UserFollowService extends IService<UserFollowModel> {
    /**
     * 关注
     * @param userId 用户id
     * @param followUserId 被关注者id
     * @return true/false
     */
    Boolean follow(Long userId, Long followUserId);

    /**
     * 取消关注
     * @param userId 用户id
     * @param followUserId 被关注者id
     * @return true/false
     */
    Boolean cancelFollow(Long userId, Long followUserId);

    /**
     * 用户关注的所有用户
     * @param userId 用户id
     * @return List<UserModel>
     */
    List<UserVO> allFollowInfo(Long userId);

    /**
     * 条件查询所有关注的用户
     * @param userId 替换id
     * @param name 名称
     * @return List<UserVO>
     */
    List<UserVO> searchFollow(Long userId, String name);
}
