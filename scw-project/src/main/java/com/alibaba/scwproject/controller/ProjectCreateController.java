package com.alibaba.scwproject.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.scwproject.bean.TReturn;
import com.alibaba.scwproject.constant.ProjectConstant;
import com.alibaba.scwproject.entity.BaseVO;
import com.alibaba.scwproject.entity.ProjectBaseInfoVO;
import com.alibaba.scwproject.entity.ProjectRedisVO;
import com.alibaba.scwproject.entity.ProjectReturnVO;
import com.alibaba.scwproject.enums.ProjectStatusEnum;
import com.alibaba.scwproject.service.ProjectCreateService;
import com.alibaba.scwproject.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import response.AppResponse;

import javax.annotation.Resource;
import java.util.List;

import static response.AppResponse.fail;

@Slf4j
@RequestMapping("/project")
@RestController
public class ProjectCreateController {
    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    ProjectCreateService projectCreateService;

    @Resource
    ProjectService projectService;

    //问题：重复刷新调用会生成多个kv对，要对redis的projectToken做判断
    @PostMapping("/init")
    public AppResponse<String> init(BaseVO baseVO) {
        System.out.println(baseVO);
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

    @PostMapping("/baseInfo")
    public AppResponse<String> projectBaseInfo(ProjectBaseInfoVO projectBaseInfoVO) {
        //基础数据判空校验
        if (!ProjectBaseInfoVO.isLogin(projectBaseInfoVO)) {
            log.error("用户信息：phone = {}, accesstoken = {}", projectBaseInfoVO.getPhone(), projectBaseInfoVO.getAccessToken());
            return AppResponse.fail("登录信息为空");
        }
        //鉴权
        String projectVOString = stringRedisTemplate.opsForValue().get(ProjectConstant.tokenConstant + projectBaseInfoVO.getProjectToken());
        if (StringUtils.isBlank(projectVOString)) {
            log.error("projectVOString = {}", projectVOString);
            return AppResponse.fail("登录态失效");
        }
        ProjectRedisVO projectRedisVO = JSON.parseObject(projectVOString, ProjectRedisVO.class);

        if (!ProjectBaseInfoVO.compareRedisVO(projectBaseInfoVO, projectRedisVO)) {
            log.error("登录态校验不一致 = phone = {}, projectToken = {}, accessToken = {}", projectBaseInfoVO.getPhone(), projectBaseInfoVO.getProjectToken(), projectBaseInfoVO.getAccessToken());
            return AppResponse.fail("登录态信息不一致");
        }

        BeanUtils.copyProperties(projectBaseInfoVO, projectRedisVO);
        stringRedisTemplate.opsForValue().set(ProjectConstant.tokenConstant + projectBaseInfoVO.getProjectToken(), JSON.toJSONString(projectRedisVO));

        return AppResponse.ok("");

    }

    @PostMapping("/reward")
    public AppResponse<String> projectReturn(@RequestBody List<ProjectReturnVO> projectReturnVOList) {
        if (projectReturnVOList.size() == 0) {
            return AppResponse.fail("没有提交内容");
        }
        ProjectReturnVO projectReturnVO = projectReturnVOList.get(0);
        String stringVO = stringRedisTemplate.opsForValue().get(ProjectConstant.tokenConstant + projectReturnVO.getProjectToken());
        ProjectRedisVO projectRedisVO = JSON.parseObject(stringVO, ProjectRedisVO.class);

        //遍历projectReturnVOList，鉴权
        for (ProjectReturnVO vo : projectReturnVOList) {
            if (!ProjectReturnVO.isLogin(vo)) {
                log.error("用户信息：phone = {}, accesstoken = {}", vo.getPhone(), vo.getAccessToken());
                return AppResponse.fail("没有登录信息");
            }

            if (!ProjectReturnVO.compareRedisVO(vo, projectRedisVO)) {
                log.error("登录态校验不一致 = phone = {}, projectToken = {}, accessToken = {}", vo.getPhone(), vo.getProjectToken(), vo.getAccessToken());
                return AppResponse.fail("登录态信息不一致");
            }
        }

        //对拷Treturn
        List<TReturn> copyList = ProjectReturnVO.copyTReturn(projectReturnVOList);
        if (copyList == null) {
            return AppResponse.fail("对拷失败");
        }
        //对拷大VO
        projectRedisVO.setProjectReturns(copyList);
        //更新redis
        stringRedisTemplate.opsForValue().set(ProjectConstant.tokenConstant + projectRedisVO.getProjectToken(), JSON.toJSONString(projectRedisVO));
        return AppResponse.ok("");
    }

    @PostMapping("/submit")
    public AppResponse<String> submitProject(@RequestHeader("accessToken") String accessToken, @RequestHeader("projectToken") String projectToken, @RequestParam("ops") String ops) {
        if (StringUtils.isBlank(accessToken) || StringUtils.isBlank(projectToken)) {
            log.error("用户信息：projectToken = {}, accesstoken = {}", projectToken, accessToken);
            return AppResponse.fail("登录信息为空");
        }
        //校验状态
        ProjectStatusEnum statusEnum = ProjectStatusEnum.of(ops);
        System.out.println(statusEnum);
        String s = stringRedisTemplate.opsForValue().get(ProjectConstant.tokenConstant + projectToken);
        if (StringUtils.isBlank(s)) {
            return AppResponse.fail("数据不存在");
        }
        
        ProjectRedisVO project = JSON.parseObject(s, ProjectRedisVO.class);
        //落库
        Integer integer = projectService.saveProjectInfo(project);
        if (integer < 0) {
            return AppResponse.fail("保存失败");
        }
        //清理缓存
        stringRedisTemplate.delete(ProjectConstant.tokenConstant + projectToken);
        return AppResponse.ok("落库成功");
    }

}
