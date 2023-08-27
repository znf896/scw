package com.alibaba.scwuser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.alibaba.scwuser.mapper")
public class ScwUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScwUserApplication.class, args);
    }

}
