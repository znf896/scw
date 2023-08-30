package com.alibaba.scwuser.controller;

import com.alibaba.scwuser.api.Login;
import com.alibaba.scwuser.vo.request.UserLoginVO;
import com.alibaba.scwuser.vo.request.UserRegisterVO;
import com.alibaba.scwuser.vo.response.UserRespVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import response.AppResponse;

import javax.annotation.Resource;

@Slf4j
@RestController
public class TestController {

    @Resource
    Login login;

    @GetMapping("/test")
    public String test() {
        log.info("log info: {}", "test controller");
        return "test ok";
    }

    @GetMapping("/auto")
    public String testAudo() {
        log.info("log info: {}", "test controller");
        System.out.println("提交分支项目");
        return "auto ok";
    }

    @PostMapping("/register")
    public AppResponse<String> register(UserRegisterVO userRegisterVO) {
        try {
            return login.register(userRegisterVO);
        } catch (Exception e) {
            return AppResponse.fail("注册异常");
        }

    }

    @PostMapping("/login")
    public AppResponse<UserRespVO> login(UserLoginVO userLoginVO) {
        try {
            return login.doLogin(userLoginVO);
        } catch (Exception e) {
            log.error(e.getMessage());
            return AppResponse.fail(null);
        }

    }
}
