package com.alibaba.scwproject.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum InvoiceEnum {
    NO_FP((byte) 0, "不开发票"),
    HAVE_FP((byte) 1, "开发票"),
    ;

    private byte code;
    private String type;

    public static InvoiceEnum of(InvoiceEnum signalPurchaseEnum) {
        InvoiceEnum res = Arrays.stream(InvoiceEnum.values())
                .filter(value -> value.code == signalPurchaseEnum.code)
                .findFirst()
                .orElse(null);
        return res;
    }

}
