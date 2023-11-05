package com.qny.common.constant;

/**
 * @author Knight
 * @since 2023/10/29
 **/

public class AuthReturnMessage {
    public final static String AUTH_EXCEPTION_FAIL = "认证失败";
    public final static String AUTH_EXCEPTION_DEPRECATED = "认证已弃用";
    public final static String AUTH_EXCEPTION_EXPIRED = "认证过期";
    public final static String AUTH_EXCEPTION_INVALID = "认证格式有误";

    public final static String AUTH_EXCEPTION_NULL = "认证信息不可为空";
    public final static String AUTH_SUCCESS_LOGOUT = "已登出";
    public final static String AUTH_SUCCESS_TIP_REFRESH = "请更换新token";
}
