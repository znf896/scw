package com.alibaba.scwproject.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.scwproject.constant.ProjectConstant;
import com.alibaba.scwproject.entity.ProjectRedisVO;
import com.alibaba.scwproject.service.ProjectCreateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

@Slf4j
@Service
public class ProjectCreateServiceImpl implements ProjectCreateService {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public ProjectRedisVO initProjectCreate(ProjectRedisVO projectRedisVO) {

        try {
            //生成projectToken
            String uuid = UUID.randomUUID().toString().replace("-", "");
            projectRedisVO.setProjectToken(uuid);
            //ProjectCreateVO保存到redis
            stringRedisTemplate.opsForValue().set(ProjectConstant.tokenConstant + uuid, JSON.toJSONString(projectRedisVO));

        } catch (Exception e) {
            log.error("ProjectCreateServiceImpl#initProjectCreate异常：{}", e.getMessage());
            return null;
        }
        return projectRedisVO;


    }
}
