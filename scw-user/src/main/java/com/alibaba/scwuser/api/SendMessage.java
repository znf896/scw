package com.alibaba.scwuser.api;

import response.AppResponse;

import java.io.IOException;

public interface SendMessage {
    AppResponse<String> sendMessage() throws IOException;
}
