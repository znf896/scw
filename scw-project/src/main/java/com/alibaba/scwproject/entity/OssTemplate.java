package com.alibaba.scwproject.entity;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.model.PutObjectRequest;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.UUID;

@Slf4j
@Data
public class OssTemplate {
    private String bucketName;
    private String endpoint;
    private String path;

    public void upload(InputStream inputStream, String fileName) throws Exception {
        // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        // 填写Object完整路径，完整路径中不能包含Bucket名称，将文件存到bucket指定目录下，例如exampledir/exampleobject.txt。
        String objectName = path + UUID.randomUUID().toString().replace("-", "") + "_" + fileName;
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);
        log.info("ossClient实例：{}", ossClient.toString());
        // 创建PutObjectRequest对象。
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, inputStream);
        log.info("PutObjectRequest对象:{}", putObjectRequest.toString());
        // 上传文件。
        ossClient.putObject(putObjectRequest);
        if (ossClient != null) {
            ossClient.shutdown();
        }

    }

}
