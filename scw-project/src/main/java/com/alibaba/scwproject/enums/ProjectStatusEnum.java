package com.alibaba.scwproject.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;

@ToString
@Getter
@AllArgsConstructor
public enum ProjectStatusEnum {

    DRAFT((byte) 0, "草稿"),
    SUBMIT_AUTH((byte) 1, "提交审核申请"),
    AUTHING((byte) 2, "后台正在审核"),
    AUTHED((byte) 3, "后台审核通过"),
    AUTHFAIL((byte) 4, "审核失败"),
    STARTING((byte) 5, "开始众筹"),//新增众筹项目一些状态
    SUCCESS((byte) 6, "众筹成功"),
    FINISHED((byte) 7, "众筹完成"),
    FAIL((byte) 8, "众筹失败"),
    ;

    private byte code;
    private String type;

    public static ProjectStatusEnum of(String ops) {
        ProjectStatusEnum res = Arrays.stream(ProjectStatusEnum.values())
                .filter(value -> StringUtils.equals(ops, value.type))
                .findFirst()
                .orElse(null);
        return res;
    }


}
