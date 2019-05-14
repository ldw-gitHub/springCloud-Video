
package com.itcast.dw.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class ResultInfo<T> {

	/**
	 * 操作成功，提示信息由前端自己展示
	 */
    public static final int SUCCESS = 200;
    /**
     * 操作失败，失败信息需要展示给其用户
     */
    public static final int FAILURE = 300;
    public static final int INVALID_PARAM = FAILURE;
    /**
     * 操作失败，失败信息需要展示给其用户，且有业务前端自己处理
     */
    public static final int FAILURE_BUSS = 301;
    /**
     * 操作失败，不展示给用户，但建议前端console打印出来
     */
    public static final int ERROR = 400;
    /**
     * 身份信息有误，需要跳转到身份验证
     */
    public static final int INVALID_TOKEN = 402;

    public static final String MSG_SUCCESS = "操作成功";
    public static final String MSG_FAILURE = "操作失败";
    public static final String MSG_INVALID_PARAM = "请求参数错误";
    public static final String MSG_ERROR = "错误";
    public static final String MSG_ADMIN_INVALID_TOKEN = "身份信息失效，请重新登录";
    public static final String MSG_INVALID_TOKEN = "身份信息失效，请您重新登录";
    public static final String MSG_FORBIDOM = "禁止访问";
    public static final String MSG_REGISTERED = "账号已被注册";
    public static final String MSG_ERROR_LOGIN = "账号或者密码错误";
    public static final String MSG_NOT_REAL = "账号未实名，请您实名后再试";
    public static final String KAPTCHA_EXPRRIE = "验证码已过期，请刷新后再试";
    public static final String KAPTCHA_NOT_REAL = "验证码错误";
    public static final String KAPTCHA_IS_NULL = "请填写验证码";
    public static final String MSG_LOGIN_FAILED = "登录失败超过限定次数，请一天后在试";
    
    //================叫号系统=提示信息============================================
    public static final String MSG_INVALID_REQUEST = "请求已过期";
    public static final String MSG_ERROR_KEY = "错误的秘钥";
    public static final String MSG_INVALID_ACCESS_TOKEN = "非法的accessToken";
    public static final String MSG_OVERTIME_ACCESS_TOKEN = "accessToken已失效";
    //================叫号系统=提示信息============================================
    
    private Integer code;
    private String message;
    private Long timestamp;
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResultInfo() {}

    public ResultInfo(Integer code, String message) {
        super();
        this.code = code;
        this.timestamp = System.currentTimeMillis();
        this.message = message;
    }

    public ResultInfo(Integer code, String message, T data) {
        super();
        this.code = code;
        this.timestamp = System.currentTimeMillis();
        this.message = message;
        this.data = data;
    }
}
