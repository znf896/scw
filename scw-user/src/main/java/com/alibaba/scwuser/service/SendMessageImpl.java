package com.alibaba.scwuser.service;

import com.alibaba.scwuser.api.SendMessage;
import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import response.AppResponse;

import java.io.IOException;
import java.util.Random;

@Slf4j
@Service
public class SendMessageImpl implements SendMessage {

    public AppResponse<String> sendMessage() throws IOException {
        String url = "https://gyytz.market.alicloudapi.com/sms/smsSend";

        //获取 OkHttpClient 对象
        OkHttpClient httpClient = new OkHttpClient.Builder().build();

        //构造body参数
//        JSONObject jsonBody = new JSONObject();
//        jsonBody.put("mobile", "17857692659");
//        jsonBody.put("templateId", "908e94ccf08b4476ba6c876d13f084ad");
//        jsonBody.put("smsSignId", "2e65b1bb3d054466b82f0c9d125465e2");
//        jsonBody.put("param", "**code**:77777,**minute**:5");
        //创建query参数
        StringBuilder param = new StringBuilder();
        param.append("**code**:")
                .append(new Random().nextInt(10))
                .append(new Random().nextInt(10))
                .append(new Random().nextInt(10))
                .append(new Random().nextInt(10))
                .append(",**minute**:5");
        log.info("验证码：{}", param.toString());


        FormBody formBody = new FormBody.Builder()
                .add("mobile", "17857692659")
                .add("templateId", "908e94ccf08b4476ba6c876d13f084ad")
                .add("smsSignId", "2e65b1bb3d054466b82f0c9d125465e2")
                .add("param", param.toString())
                .build();

        //创建请求聚合
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .addHeader("Authorization", "APPCODE " + "5864cd085d4c459f8ec5be170550e162")
                .header("Content-Type", "application/json")
                .build();

        //发送post请求
        Response response = httpClient.newCall(request).execute();
        log.info("response: {}", response);
        log.info("response.code: {}", response.code());
        log.info("response.message: {}", response.message());

        if (response.isSuccessful()) {
            AppResponse<String> ok = AppResponse.ok(response.body().string());
            return ok;
        } else {
            return AppResponse.fail("调用失败");
        }


    }


}
