package com.qny.video.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 响应消息
 * @author ttpfx
 * @since 2023/10/27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result implements Serializable {
    // 响应状态码
    private Integer code;
    // 响应消息
    private String message;
    // 响应数据
    private Object data;

    /**
     * 成功
     * @return 200状态码
     */
    public static Result ok(){
        return new Result(200,"success",null);
    }
    /**
     * 成功
     * @return 200 状态码，附带data
     */
    public static Result ok(Object data){
        return new Result(200,"success",data);
    }
    /**
     * 失败
     * @return 500 状态码
     */
    public static Result fail(){
        return new Result(500,"fail",null);
    }
    /**
     * 失败
     * @return 500状态码，附带data
     */
    public static Result fail(Object data){
        return new Result(500,"fail",data);
    }

    public static Result fail(Integer code, String message) {
        return new Result(code,message,null);
    }
}
