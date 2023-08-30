package com.alibaba.scwuser.service;

import com.alibaba.scwuser.api.Login;
import com.alibaba.scwuser.api.domain.UserRepository;
import com.alibaba.scwuser.assembler.UserAssembler;
import com.alibaba.scwuser.dao.RegisterDAO;
import com.alibaba.scwuser.enums.RegisterEnum;
import com.alibaba.scwuser.mapper.TMemberMapper;
import com.alibaba.scwuser.vo.UserRegisterVO;
import enums.CodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import response.AppResponse;

import javax.annotation.Resource;

@Slf4j
@Service
public class LoginImpl implements Login {
    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    UserRepository userRepository;

    @Resource
    UserAssembler userAssembler;

    @Resource
    TMemberMapper tMemberMapper;


    @Override
    public AppResponse<String> register(UserRegisterVO userRegisterVO) {
        //校验loginacct唯一性和非空
        if (StringUtils.isBlank(userRegisterVO.getLoginacct())) {
            log.error("账号信息：{}", userRegisterVO.getLoginacct());
            return AppResponse.fail(RegisterEnum.ERROR.getMessage());
        }

        //校验邮件
        if (StringUtils.isBlank(userRegisterVO.getLoginacct())) {
            log.error("邮件信息：{}", userRegisterVO.getEmail());
            return AppResponse.fail(RegisterEnum.ERROR.getMessage());
        }
        String loginacct = tMemberMapper.findByEmail(userRegisterVO.getEmail());
        if (StringUtils.isNotBlank(loginacct)) {
            return AppResponse.fail("邮箱已存在");
        }


        //校验验证码是否在有效期
        String code = stringRedisTemplate.opsForValue().get(userRegisterVO.getLoginacct());
        if (StringUtils.isBlank(code)) {
            log.error("验证码：{}", userRegisterVO.getCode());
            return AppResponse.fail(CodeEnum.EXPIRED.getMsg());
        }

        //密码加密
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String mixPsd = encoder.encode(userRegisterVO.getUserpswd());
        userRegisterVO.setUserpswd(mixPsd);
        log.info("加密密码：{}", mixPsd);

        //VO -> DO
        RegisterDAO registerDAO = userAssembler.convertUserRegisterVO2RegisterDAO(userRegisterVO);

        //保存用户
        try {
            userRepository.saveRegitser(registerDAO);
            log.info("用户保存成功 {}", registerDAO);
            return AppResponse.ok(RegisterEnum.SUCCESS.getMessage());
        } catch (Exception e) {
            log.error("保存失败 {}", e.getMessage());
            return AppResponse.ok(RegisterEnum.FAIL.getMessage());
        }

    }
}
