package com.qny.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qny.user.mapper.UserMapper;
import com.qny.user.service.UserService;
import com.qny.common.domain.model.UserModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ttpfx
 * @since 2023/11/1
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserModel> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserModel getUserById(Long userId) {
        // 通过id查询
        return userMapper.selectById(userId);
    }

    @Override
    public List<UserModel> getUserByIds(List<Long> userIds) {
        return userMapper.selectBatchIds(userIds);
    }
}
