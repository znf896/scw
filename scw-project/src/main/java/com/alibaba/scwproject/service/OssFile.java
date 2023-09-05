package com.alibaba.scwproject.service;

import java.io.InputStream;

public interface OssFile {
    public String upload(InputStream inputStream, String fileName) throws Exception;
}
