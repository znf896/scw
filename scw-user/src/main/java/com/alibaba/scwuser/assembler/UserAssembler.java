package com.alibaba.scwuser.assembler;

import com.alibaba.scwuser.dao.RegisterDAO;
import com.alibaba.scwuser.enums.AuthEnum;
import com.alibaba.scwuser.enums.UserTypeEnum;
import com.alibaba.scwuser.vo.UserRegisterVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class UserAssembler {

    public RegisterDAO convertUserRegisterVO2RegisterDAO(UserRegisterVO userRegisterVO) {
        RegisterDAO registerDAO = new RegisterDAO();
        BeanUtils.copyProperties(userRegisterVO, registerDAO);
        registerDAO.setAuthstatus(AuthEnum.AUTHORIZED.getAuthStatus());
        registerDAO.setUsername(String.valueOf(new Random().nextInt(9000) + 1000));
        registerDAO.setUsertype(UserTypeEnum.USER.getType());
        return registerDAO;
    }

}
