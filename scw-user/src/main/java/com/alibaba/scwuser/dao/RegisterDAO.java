package com.alibaba.scwuser.dao;

import lombok.Data;

@Data
public class RegisterDAO {
    private Integer id;
    private String loginacct;
    private String userpswd;
    private String username;
    private String email;
    private String authstatus;
    private String usertype;
    private String realname;
    private String cardnum;
    private String accttype;


}
