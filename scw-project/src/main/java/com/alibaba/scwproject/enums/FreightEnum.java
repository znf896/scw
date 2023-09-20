package com.alibaba.scwproject.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum FreightEnum {
    FREE_FREIGHT((byte) 0, "免邮"),
    CHARGE_FREIGHT((byte) 1, "不包邮"),
    ;

    private byte code;
    private String type;

    public static FreightEnum of(FreightEnum signalPurchaseEnum) {
        FreightEnum res = Arrays.stream(FreightEnum.values())
                .filter(value -> value.code == signalPurchaseEnum.code)
                .findFirst()
                .orElse(null);
        return res;
    }


}
