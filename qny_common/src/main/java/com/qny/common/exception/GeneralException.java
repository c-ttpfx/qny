package com.qny.common.exception;

/**
 * @author Knight
 * @since 2023/10/29
 *
 * 通用异常类
 **/

public class GeneralException  extends RuntimeException {
    /**
     * 无参数构造方法，创建一个没有详细错误消息的 GeneralException。
     */
    public GeneralException() {
        super();
    }

    /**
     * 构造方法，根据指定的详细错误消息创建一个 GeneralException。
     * @param message 详细错误消息
     */
    public GeneralException(String message) {
        super(message);
    }

    /**
     * 构造方法，根据指定的详细错误消息和原始异常创建一个 GeneralException。
     * @param message 详细错误消息
     * @param cause 原始异常
     */
    public GeneralException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 构造方法，根据原始异常创建一个 GeneralException。
     * @param cause 原始异常
     */
    public GeneralException(Throwable cause) {
        super(cause);
    }
}
