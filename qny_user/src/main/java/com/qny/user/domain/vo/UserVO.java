package com.qny.user.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * @author Knight
 * @since 2023/11/7
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserVO implements Serializable {
    private static final long serialVersionUID = 1234567890L;

    // 用户id
    private String userId;
    // 用户名
    private String name;
    // 昵称
    private String nickname;
    // 年龄
    private Integer age;
    // 性别
    private String gender;
    // 图标
    private String icon;
    // 关注
    private Long follow;
    // 粉丝
    private Long fans;
    // 获赞
    private Long like;
    // 视频号
    private String videoNum;
    // 个人描述
    private String description;
}
