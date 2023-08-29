package com.alibaba.scwuser.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum AuthEnum {
    UNAUTHORIZED("0", "未认证"),

    AUTHORIZED("1", "已认证");

    private String authStatus;
    private String authInfo;

    AuthEnum of(String authStatus) {
        return Arrays.stream(AuthEnum.values()).filter(a -> a.getAuthStatus().equals(authStatus)).findFirst().orElse(null);
    }


}
