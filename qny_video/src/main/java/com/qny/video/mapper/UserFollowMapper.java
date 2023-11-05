package com.qny.video.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qny.video.domain.model.UserFollowModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ttpfx
 * @since 2023/11/6
 */
@Mapper
public interface UserFollowMapper extends BaseMapper<UserFollowModel> {
}
