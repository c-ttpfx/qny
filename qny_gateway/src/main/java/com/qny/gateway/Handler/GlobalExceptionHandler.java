package com.qny.gateway.Handler;

import com.qny.video.domain.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Knight
 * @since 2023/10/29
 * 全局异常处理器
 **/

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Result resolveException(RuntimeException runtimeException) {
        // 打印完整日志
        runtimeException.printStackTrace();
        log.error("异常信息{}", runtimeException.getMessage());
        Result result = new Result();
        result.setCode(401);
        result.setData(runtimeException.getMessage());
        result.setMessage(runtimeException.getMessage());
        return result;
    }

}
