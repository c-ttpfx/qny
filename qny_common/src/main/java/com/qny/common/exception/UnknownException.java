package com.qny.common.exception;

/**
 * 自定义未知异常类，用于处理未知或未预期的异常情况。
 * @author ttpfx
 * @since 2023/10/28
 */
public class UnknownException extends Exception {

    /**
     * 无参数构造方法，创建一个没有详细错误消息的UnknownException。
     */
    public UnknownException() {
        super();
    }

    /**
     * 构造方法，根据指定的详细错误消息创建一个UnknownException。
     * @param message 详细错误消息
     */
    public UnknownException(String message) {
        super(message);
    }

    /**
     * 构造方法，根据指定的详细错误消息和原始异常创建一个UnknownException。
     * @param message 详细错误消息
     * @param cause 原始异常
     */
    public UnknownException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 构造方法，根据原始异常创建一个UnknownException。
     * @param cause 原始异常
     */
    public UnknownException(Throwable cause) {
        super(cause);
    }
}


