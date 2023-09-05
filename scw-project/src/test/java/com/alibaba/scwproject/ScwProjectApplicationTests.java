package com.alibaba.scwproject;

import com.aliyun.oss.common.utils.AuthUtils;
import com.aliyun.oss.common.utils.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ScwProjectApplicationTests {


    @Test
    void contextLoads() {
        String accessKeyId = StringUtils.trim(System.getenv(AuthUtils.ACCESS_KEY_ENV_VAR));
        System.out.println(accessKeyId);

    }

}
