package enums;

import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum CodeEnum {
    EXPIRED(0, "已过期"),

    UNEXPIRED(1, "未过期"),

    NOTEXIST(4, "不存在"),
    ;

    private Integer code;

    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    CodeEnum of(CodeEnum codeEnum) {
        return Arrays.stream(CodeEnum.values()).filter(c -> c.name().equals(codeEnum.name())).findFirst().orElse(null);
    }


}
