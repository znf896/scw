package com.alibaba.scwproject.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.scwproject.entity.BaseVO;
import com.alibaba.scwproject.entity.ProjectRedisVO;
import com.alibaba.scwproject.service.ProjectCreateService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import response.AppResponse;

import javax.annotation.Resource;

import static response.AppResponse.fail;

@RequestMapping("/project")
@RestController
public class ProjectCreateController {
    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    ProjectCreateService projectCreateService;

    //问题：重复刷新调用会生成多个kv对，要对redis的projectToken做判断
    @PostMapping("/init")
    public AppResponse<String> init(BaseVO baseVO) {
        String accessToken = baseVO.getAccessToken();
        String phone = baseVO.getPhone();
        String token = stringRedisTemplate.opsForValue().get(phone);

        if (StringUtils.isBlank(token) || !token.equals(accessToken)) {
            return fail("用户未登录");
        }

        ProjectRedisVO projectRedisVO = new ProjectRedisVO();
        BeanUtils.copyProperties(baseVO, projectRedisVO);
        ProjectRedisVO result = projectCreateService.initProjectCreate(projectRedisVO);
        if (null == result) {
            return fail("保存token异常");
        }
        String r = JSON.toJSONString(result);

        return AppResponse.ok(r);

    }
}
