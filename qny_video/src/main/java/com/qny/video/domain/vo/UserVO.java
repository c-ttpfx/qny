package com.qny.video.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ttpfx
 * @since 2023/11/6
 */
@Data
public class UserVO implements Serializable {
    private static final long serialVersionUID = 1234567890L;

    // 用户id
    private String userId;
    // 用户名
    private String name;
    // 年龄
    private Integer age;
    // 性别
    private String gender;
    // 图标
    private String icon;
    // 在线信息
    private String status;
}
