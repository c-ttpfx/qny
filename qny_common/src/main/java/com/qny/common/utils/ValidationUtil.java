package com.qny.common.utils;

import com.qny.common.exception.VerifyException;
import org.springframework.validation.BindingResult;

/**
 * 字段校验工具类
 * @author ttpfx
 * @since 2023/10/28
 */
public class ValidationUtil {

    /**
     * 校验字段信息
     * @param bindingResult BindingResult
     */
    public static void check(BindingResult bindingResult) throws VerifyException {
        if (bindingResult.hasErrors()) {
            throw new VerifyException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
    }
}
