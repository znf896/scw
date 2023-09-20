package com.alibaba.scwproject.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum SignalPurchaseEnum {
    Unlimited((byte) 0, "不限购"),
    Limited((byte) 1, "限购"),
    ;

    private byte code;
    private String type;

    public static SignalPurchaseEnum of(SignalPurchaseEnum signalPurchaseEnum) {
        SignalPurchaseEnum res = Arrays.stream(SignalPurchaseEnum.values())
                .filter(value -> value.code == signalPurchaseEnum.code)
                .findFirst()
                .orElse(null);
        return res;
    }

}
