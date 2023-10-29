package com.qny.user.exception;

import com.qny.video.domain.entity.Result;
import com.qny.video.exception.GeneralException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Knight
 * @since 2023/10/29
 *
 * 全局异常处理类
 **/

@ControllerAdvice
@RestController
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理通用异常
     * @param ex 通用异常
     * @return 固定返回格式
     */
    @ExceptionHandler(GeneralException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result handleGeneralException(GeneralException ex) {
        return Result.fail(ex.getMessage());
    }

}
