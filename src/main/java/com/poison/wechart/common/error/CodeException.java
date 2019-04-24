package com.poison.wechart.common.error;

public class CodeException extends RuntimeException {
    private int code;

    public CodeException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
