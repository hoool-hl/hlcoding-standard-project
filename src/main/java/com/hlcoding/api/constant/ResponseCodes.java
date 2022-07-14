package com.hlcoding.api.constant;

/**
 * 响应状态码
 *
 * @author ft <futao@mysteel.com>
 * @since 2021/4/21
 */
public class ResponseCodes {
    /**
     * 正常
     */
    public static final int SUCCESS = 0;
    /**
     * 逻辑异常
     */
    public static final int LOGIC_FAIL = -1;

    public static final int NOT_LOGIN = 401;
    /**
     * 系统异常
     */
    public static final int SYSTEM_ERROR_FAIL = 500;

    private ResponseCodes() {
    }
}
