package com.alibaba.scwproject.impl;

import com.alibaba.scwproject.entity.OssTemplate;
import com.alibaba.scwproject.service.OssFile;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;

@Slf4j
@Service
public class OssFileImpl implements OssFile {
    @Resource
    OssTemplate ossTemplate;

    public String upload(InputStream inputStream, String fileName) throws Exception {
        if (StringUtils.isBlank(String.valueOf(inputStream))) {
            return "fail";
        }
        if (StringUtils.isBlank(fileName)) {
            return "fail";
        }

        System.out.println("ossTemplate属性:" + ossTemplate.getEndpoint() + "\t" + ossTemplate.getBucketName());
        ossTemplate.upload(inputStream, fileName);
        return "ok";

    }
}
