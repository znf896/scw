package com.alibaba.scwuser.service;

import com.alibaba.scwuser.api.Login;
import com.alibaba.scwuser.api.domain.UserRepository;
import com.alibaba.scwuser.assembler.UserAssembler;
import com.alibaba.scwuser.dao.UserDAO;
import com.alibaba.scwuser.entity.TMember;
import com.alibaba.scwuser.enums.RegisterEnum;
import com.alibaba.scwuser.mapper.TMemberMapper;
import com.alibaba.scwuser.vo.request.UserLoginVO;
import com.alibaba.scwuser.vo.request.UserRegisterVO;
import com.alibaba.scwuser.vo.response.UserRespVO;
import enums.CodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import response.AppResponse;

import javax.annotation.Resource;
import java.util.UUID;

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
        if (!code.equalsIgnoreCase(userRegisterVO.getCode())) {
            log.error("验证码：{}", userRegisterVO.getCode());
            return AppResponse.fail(CodeEnum.NOTEXIST.getMsg());
        }

        //密码加密
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String mixPsd = encoder.encode(userRegisterVO.getUserpswd());
        userRegisterVO.setUserpswd(mixPsd);
        log.info("加密密码：{}", mixPsd);

        //VO -> DO
        UserDAO userDAO = userAssembler.convertUserRegisterVO2UserDAO(userRegisterVO);

        //保存用户
        try {
            userRepository.saveRegitser(userDAO);
            log.info("用户保存成功 {}", userDAO);
            return AppResponse.ok(RegisterEnum.SUCCESS.getMessage());
        } catch (Exception e) {
            log.error("保存失败 {}", e.getMessage());
            return AppResponse.ok(RegisterEnum.FAIL.getMessage());
        }

    }

    @Override
    public AppResponse<UserRespVO> doLogin(UserLoginVO userLoginVO) {
        //校验用户名
        if (StringUtils.isBlank(userLoginVO.getUsername())) {
            log.error("用户名为空");
            return AppResponse.fail(null);
        }

        UserDAO userDAO = userAssembler.convertUserUserLoginVO2UserDAO(userLoginVO);

        //校验密码
        TMember tMember = userRepository.findByUserName(userDAO);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(userDAO.getUserpswd(), tMember.getUserpswd())) {
            log.error("输入密码不正确 ：{}", userDAO.getUserpswd());
            return AppResponse.fail(null);
        }
        BeanUtils.copyProperties(tMember, userDAO);

        //生成令牌
        UserRespVO userRespVO = userAssembler.convertUserDAO2UserRespVO(userDAO);
        String token = UUID.randomUUID().toString().replace("-", "");
        userRespVO.setToken(token);
        log.info("登录成功：{}", userRespVO);

        return AppResponse.ok(userRespVO);
    }
}
