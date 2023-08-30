package com.alibaba.scwuser.api.domain;

import com.alibaba.scwuser.dao.UserDAO;
import com.alibaba.scwuser.entity.TMember;

public interface UserRepository {
    int saveRegitser(UserDAO userDAO);

    TMember findByUserName(UserDAO userDAO);
}
