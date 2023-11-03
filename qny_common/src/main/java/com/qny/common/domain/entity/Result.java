package com.qny.common.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 响应消息
 *
 * @author ttpfx
 * @since 2023/10/27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {
    // 响应状态码
    private Integer code;
    // 响应消息
    private String message;
    // 响应数据
    private T data;

    /**
     * 成功
     *
     * @return 200状态码
     */
    public static <T> Result<T> ok() {
        return new Result<T>(200, "success", null);
    }

    /**
     * 成功
     *
     * @return 200 状态码，附带data
     */
    public static <T> Result<T> ok(T data) {
        return new Result<T>(200, "success", data);
    }

    /**
     * 失败
     *
     * @return 500 状态码
     */
    public static <T> Result<T> fail() {
        return new Result<T>(500, "fail", null);
    }

    /**
     * 失败
     *
     * @return 500状态码，附带data
     */
    public static <T> Result<T> fail(T data) {
        return new Result<T>(500, "fail", data);
    }

    public static <T> Result<T> fail(Integer code, String message) {
        return new Result<T>(code, message, null);
    }
}
