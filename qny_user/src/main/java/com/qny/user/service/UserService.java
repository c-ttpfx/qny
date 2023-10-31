package com.qny.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qny.video.domain.model.UserModel;

import java.util.List;

/**
 * @author ttpfx
 * @since 2023/11/1
 */
public interface UserService extends IService<UserModel> {

    /**
     * 通过id查询用户
     * @param userId 用户id
     * @return 用户信息
     */
    UserModel getUserById(Long userId);

    /**
     * 通过id集合查询用户
     * @param userIds 用户id集合
     * @return  List<UserModel>
     */
    List<UserModel> getUserByIds(List<Long> userIds);
}
