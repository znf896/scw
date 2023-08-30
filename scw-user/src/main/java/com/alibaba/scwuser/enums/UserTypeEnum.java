package com.alibaba.scwuser.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserTypeEnum {
    USER("1"),
    ENTERPRISE("2");

    private final String type;
}
