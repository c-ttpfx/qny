package com.qny.video.domain.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ttpfx
 * @since 2023/11/1
 */
@Data
@TableName("user")
public class UserModel implements Serializable {
    private static final long serialVersionUID = 1234567890L;
    // 用户id
    @TableId
    private Long userId;
    // 用户名
    private String name;
    // 密码
    private String password;
    // 年龄
    private Integer age;
    // 性别
    private String gender;
    // 图标
    private String icon;
}
