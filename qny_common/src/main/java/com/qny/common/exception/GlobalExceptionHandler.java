package com.qny.common.exception;

import com.qny.common.domain.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ttpfx
 * @since 2023/10/28
 */
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

    /**
     * 处理未知异常UnknownException
     * @return Result
     */
    @ExceptionHandler(UnknownException.class)
    @ResponseBody
    public Result unknownException(Exception e, HttpServletRequest request){
        e.printStackTrace();
        return Result.fail(500,e.getMessage());
    }

    /**
     * 处理校验异常VerifyException
     * @return Result
     */
    @ExceptionHandler(VerifyException.class)
    @ResponseBody
    public Result verifyException(Exception e, HttpServletRequest request){
        e.printStackTrace();
        return Result.fail(500,e.getMessage());
    }

    /**
     * 系统异常
     * @return Result
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result exception(Exception e, HttpServletRequest request){
        e.printStackTrace();
        return Result.fail(555,"系统异常，请联系管理员");
    }
    /**
     * 请求缺少字段异常
     * @return Result
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public Result MissingServletRequestParameterException(Exception e, HttpServletRequest request){
        e.printStackTrace();
        return Result.fail(556,"请求缺少字段");
    }
}
