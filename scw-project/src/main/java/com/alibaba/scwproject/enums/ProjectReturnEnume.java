package com.alibaba.scwproject.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ProjectReturnEnume {
    VIRTUAL((byte) 0, "虚拟物品"),
    REAL((byte) 1, "实物回报"),
    ;

    private byte code;
    private String type;

    public static ProjectReturnEnume of(ProjectReturnEnume projectReturnEnume) {
        ProjectReturnEnume res = Arrays.stream(ProjectReturnEnume.values())
                .filter(value -> value.getCode() == projectReturnEnume.getCode())
                .findFirst()
                .orElse(null);
        return res;
    }

}
