package com.alibaba.scwuser.assembler;

import com.alibaba.scwuser.dao.UserDAO;
import com.alibaba.scwuser.enums.AuthEnum;
import com.alibaba.scwuser.enums.UserTypeEnum;
import com.alibaba.scwuser.vo.request.UserLoginVO;
import com.alibaba.scwuser.vo.request.UserRegisterVO;
import com.alibaba.scwuser.vo.response.UserRespVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import utils.AssembleerUtils;

import java.util.Random;

@Component
public class UserAssembler {

    public UserDAO convertUserRegisterVO2UserDAO(UserRegisterVO userRegisterVO) {
        UserDAO registerDAO = new UserDAO();
        BeanUtils.copyProperties(userRegisterVO, registerDAO);
        registerDAO.setAuthstatus(AuthEnum.AUTHORIZED.getAuthStatus());
        registerDAO.setUsername(String.valueOf(new Random().nextInt(9000) + 1000));
        registerDAO.setUsertype(UserTypeEnum.USER.getType());
        return registerDAO;
    }

    public UserDAO convertUserUserLoginVO2UserDAO(UserLoginVO userLoginVO) {
        UserDAO userDAO = new UserDAO();
        userDAO.setUsername(userLoginVO.getUsername());
        userDAO.setUserpswd(userLoginVO.getPassword());
        return userDAO;
    }

    public UserRespVO convertUserDAO2UserRespVO(UserDAO userDAO) {
        UserRespVO userRespVO = new UserRespVO();
        BeanUtils.copyProperties(userDAO, userRespVO);
        String token = AssembleerUtils.genRandomToken();
        userRespVO.setToken(token);
        return userRespVO;
    }

}
