package response;

import enums.ResponseEnum;
import lombok.Data;

@Data
public class AppResponse<T> {
    private String msg;
    private Integer code;
    private T data;

    public static <T> AppResponse<T> ok(T data) {
        AppResponse<T> response = new AppResponse<>();
        response.setCode(ResponseEnum.SUCCESS.getCode());
        response.setMsg(ResponseEnum.SUCCESS.getMsg());
        response.setData(data);
        return response;
    }

    public static <T> AppResponse<T> fail(T data) {
        AppResponse<T> response = new AppResponse<>();
        response.setCode(ResponseEnum.FAIL.getCode());
        response.setMsg(ResponseEnum.FAIL.getMsg());
        response.setData(data);
        return response;
    }

}
