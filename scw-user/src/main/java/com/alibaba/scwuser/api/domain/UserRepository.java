package com.alibaba.scwuser.api.domain;

import com.alibaba.scwuser.dao.RegisterDAO;

public interface UserRepository {
    int saveRegitser(RegisterDAO registerDAO);
}
