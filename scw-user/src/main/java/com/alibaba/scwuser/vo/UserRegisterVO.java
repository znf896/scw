package com.alibaba.scwuser.vo;

import lombok.Data;

@Data
public class UserRegisterVO {

    /**
     * 手机号
     */
    private String loginacct;

    /**
     * 密码
     */
    private String userpswd;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 验证码
     */
    private String code;
}
