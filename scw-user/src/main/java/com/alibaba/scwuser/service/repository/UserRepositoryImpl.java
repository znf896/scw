package com.alibaba.scwuser.service.repository;

import com.alibaba.scwuser.api.domain.UserRepository;
import com.alibaba.scwuser.dao.UserDAO;
import com.alibaba.scwuser.entity.TMember;
import com.alibaba.scwuser.mapper.TMemberMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Repository
@Transactional(readOnly = true)
public class UserRepositoryImpl implements UserRepository {
    @Resource
    TMemberMapper tMemberMapper;

    @Override
    public TMember findByUserName(UserDAO userDAO) {
        return tMemberMapper.selectByUsername(userDAO);
    }

    @Transactional
    @Override
    public int saveRegitser(UserDAO userDAO) {
        TMember tMember = new TMember();
        BeanUtils.copyProperties(userDAO, tMember);
        return tMemberMapper.insert(tMember);
    }
}
