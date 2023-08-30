package com.alibaba.scwuser.vo.response;

import lombok.Data;

@Data
public class UserRespVO {
    private Integer id;
    /**
     * 手机号
     */
    private String loginacct;
    /**
     * 密码
     */
    private String userpswd;
    /**
     * 用户名
     */
    private String username;
    private String email;
    private String authstatus;
    private String usertype;
    private String realname;
    private String cardnum;
    private String accttype;
    private String token;
}
