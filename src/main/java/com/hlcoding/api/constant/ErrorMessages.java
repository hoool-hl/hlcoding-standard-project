package com.hlcoding.api.constant;

/**
 * @author ft <futao@mysteel.com>
 * @date 2021/8/17
 */
public class ErrorMessages {
    private ErrorMessages() {
    }

    public static final String PREUID_NOT_EMPTY = "preUid不能为空";
    public static final String REQ_NOT_NULL = "请求参数不能为空";
    public static final String NOT_LOGIN = "您还未登录或者登录已超时，请重新登录";
    public static final String SYSTEM_ERROR = "系统繁忙，请稍后再试";

    public static final String CANDIDATE_NOT_FOUND = "候选人信息不存在";
}
