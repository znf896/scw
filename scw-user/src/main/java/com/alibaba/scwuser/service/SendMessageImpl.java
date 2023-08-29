package com.alibaba.scwuser.service;

import com.alibaba.scwuser.api.SendMessage;
import enums.CodeEnum;
import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import response.AppResponse;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class SendMessageImpl implements SendMessage {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    public AppResponse<String> sendMessage(String phone) throws IOException {
        if (stringRedisTemplate.opsForValue().get(phone) != null) {
            return AppResponse.fail(CodeEnum.UNEXPIRED.getMsg());
        }

        String url = "https://gyytz.market.alicloudapi.com/sms/smsSend";

        //获取 OkHttpClient 对象
        OkHttpClient httpClient = new OkHttpClient.Builder().build();

        String code = randInt();//返回随机数

        //创建query参数
        StringBuilder param = new StringBuilder();
        param.append("**code**:")
                .append(code)
                .append(",**minute**:5");

        stringRedisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
        log.info("验证码：{}", param.toString());

        FormBody formBody = new FormBody.Builder()
                .add("mobile", phone)
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

    private String randInt() {
        int i = new Random().nextInt(9000);
        return String.valueOf(i + 1000);

    }


}
