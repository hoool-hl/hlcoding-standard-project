package com.hlcoding.api.exception;

import com.hlcoding.api.constant.ResponseCodes;
import lombok.Getter;

/**
 * 逻辑异常
 *
 */
@Getter
public class LogicException extends RuntimeException {
    private final Integer code;

    private LogicException(String message, int code) {
        super(message);
        this.code = code;
    }

    public static LogicException le(String msg, int code) {
        return new LogicException(msg, code);
    }

    public static LogicException le(String msg) {
        return le(msg, ResponseCodes.LOGIC_FAIL);
    }

    public static void leThrow(String msg) {
        throw le(msg, ResponseCodes.LOGIC_FAIL);
    }
}
