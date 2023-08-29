package com.alibaba.scwuser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.scwuser.api.SendMessage;
import com.alibaba.scwuser.entity.Message;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import response.AppResponse;

import javax.annotation.Resource;
import java.io.IOException;

@SpringBootTest
class ScwUserApplicationTests {
    @Resource
    RedisTemplate<Object, Object> redisTemplate;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    SendMessage sendMessage;


    @Test
    void testRedis() {
        redisTemplate.opsForValue().set("condition", "name2");
        stringRedisTemplate.opsForValue().set("address", "home2");
    }

    @Test
    void testMessage() {
        try {
            AppResponse<String> responseBodyAppResponse = sendMessage.sendMessage("17857692659");
            System.out.println("AppResponse: " + responseBodyAppResponse);
            String res = JSON.toJSONString(responseBodyAppResponse);
            System.out.println(res);
            //逐级分解里面的参数
            JSONObject jsonObject = JSON.parseObject(res);
            String msg = jsonObject.getString("msg");
            int code = jsonObject.getIntValue("code");
            String data = jsonObject.getString("data");
            //分解data参数
            JSONObject object = JSON.parseObject(data);
            String smsid = object.getString("smsid");
            String balance = object.getString("balance");

            System.out.println("msg: " + msg);
            System.out.println("code: " + code);
            System.out.println("data: " + data);
            System.out.println("smsid: " + smsid);
            System.out.println("balance: " + balance);

            //通过对象属性映射
            System.out.println("------分割线--------");
            Message message = JSON.parseObject(data, Message.class);
            System.out.println("message：" + message);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
