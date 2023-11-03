package com.qny.common.exception;

/**
 * 自定义验证异常类，用于处理验证相关的异常情况。
 * @author ttpfx
 * @since 2023/10/28
 */
public class VerifyException extends Exception {

    /**
     * 无参数构造方法，创建一个没有详细错误消息的VerifyException。
     */
    public VerifyException() {
        super();
    }

    /**
     * 构造方法，根据指定的详细错误消息创建一个VerifyException。
     * @param message 详细错误消息
     */
    public VerifyException(String message) {
        super(message);
    }

    /**
     * 构造方法，根据指定的详细错误消息和原始异常创建一个VerifyException。
     * @param message 详细错误消息
     * @param cause 原始异常
     */
    public VerifyException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 构造方法，根据原始异常创建一个VerifyException。
     * @param cause 原始异常
     */
    public VerifyException(Throwable cause) {
        super(cause);
    }
}

