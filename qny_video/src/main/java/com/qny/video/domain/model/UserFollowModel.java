package com.qny.video.domain.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ttpfx
 * @since 2023/11/6
 */
@Data
@TableName("user_follow")
public class UserFollowModel implements Serializable {
    private static final long serialVersionUID = 1234567890L;

    // 用户id
    private Long userId;
    // 关注者id
    private Long followUserId;
    // 关注日期
    private Long followDate;
}
