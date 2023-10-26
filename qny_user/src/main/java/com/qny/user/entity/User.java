package com.qny.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Knight
 * @since 2023/10/26
 * 用户实体
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    Long Id;
    String username;
    String password;
}
