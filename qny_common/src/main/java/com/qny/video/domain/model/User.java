package com.qny.video.domain.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ttpfx
 * @since 2023/10/27
 */
@Data
public class User implements Serializable {

    private Long userId;

    private String name;

    private String age;

    private String gender;

    private String password;
}
