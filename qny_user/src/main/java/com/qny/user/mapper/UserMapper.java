package com.qny.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qny.common.domain.model.UserModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ttpfx
 * @since 2023/11/1
 */
@Mapper
public interface UserMapper extends BaseMapper<UserModel> {
}
