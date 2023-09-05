package com.alibaba.scwproject.controller;

import com.alibaba.scwproject.service.OssFile;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import response.AppResponse;

import javax.annotation.Resource;

@Slf4j
@RequestMapping("/project")
@RestController
public class OssDemoController {
    @Resource
    OssFile ossFile;

    @PostMapping("/test")
    public AppResponse<String> upload(@RequestParam("file") MultipartFile[] multipartFiles) {
        for (MultipartFile m : multipartFiles) {
            try {
                String result = ossFile.upload(m.getInputStream(), m.getOriginalFilename());
                if ("fail".equals(result)) {
                    return AppResponse.fail("上传失败");
                }
            } catch (OSSException oe) {
                log.error("上传失败：{}, Error Code:{}, Request ID: {},Host ID: {}", oe.getMessage(), oe.getErrorCode(), oe.getRequestId(), oe.getHostId());
                return AppResponse.fail("上传失败");
            } catch (ClientException ce) {
                log.error("OSS客户端异常: {}", ce.getMessage());
                return AppResponse.fail("上传失败");

            } catch (Exception e) {
                return AppResponse.fail("上传失败");

            }
        }
        return AppResponse.ok("上传成功");
    }

    @GetMapping("/test2")
    public String test() {
        return "ok test2";
    }
}

