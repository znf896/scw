package com.alibaba.scwproject.config;

import com.alibaba.scwproject.entity.OssTemplate;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class OssConfig {

    @ConfigurationProperties(prefix = "oss")
    @Bean
    public OssTemplate ossTemplate() {
        return new OssTemplate();

    }


}
