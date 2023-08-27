package com.alibaba.scwuser.entity;

import lombok.Data;

@Data
public class Message {
    /**
     * 短信签名ID
     */
    private String smSid;

    /**
     * 短信剩余次数
     */
    private String balance;

}
