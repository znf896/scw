package com.alibaba.scwuser.api;

import com.alibaba.scwuser.vo.request.UserLoginVO;
import com.alibaba.scwuser.vo.request.UserRegisterVO;
import com.alibaba.scwuser.vo.response.UserRespVO;
import response.AppResponse;

public interface Login {
    AppResponse<String> register(UserRegisterVO userRegisterVO);

    AppResponse<UserRespVO> doLogin(UserLoginVO userLoginVo);

}
