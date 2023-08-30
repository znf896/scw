package com.alibaba.scwuser.api;

import com.alibaba.scwuser.vo.UserRegisterVO;
import response.AppResponse;

public interface Login {
    AppResponse<String> register(UserRegisterVO userRegisterVO);

}
