package com.alibaba.scwuser.vo.request;

import lombok.Data;

@Data
public class UserLoginVO {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;


}
