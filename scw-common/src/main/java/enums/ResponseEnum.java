package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseEnum {
    SUCCESS(1, "success"),

    FAIL(-1, "fail"),

    NOT_FOUND(404, "资源未找到"),

    NOT_AUTHED(403, "无权限，访问拒绝"),

    PARAM_INVAILD(400, "提交参数非法");

    private Integer code;

    private String msg;


}
