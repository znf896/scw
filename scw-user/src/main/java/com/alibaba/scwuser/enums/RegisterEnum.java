package com.alibaba.scwuser.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum RegisterEnum {
    ERROR(0, "数据为空或信息错误"),
    REPEATED(1, "重复"),
    FAIL(3, "注册失败"),
    SUCCESS(4, "注册成功");

    private int code;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
