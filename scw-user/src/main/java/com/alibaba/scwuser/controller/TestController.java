package com.alibaba.scwuser.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

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
}
