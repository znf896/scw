package com.alibaba.scwuser.service.repository;

import com.alibaba.scwuser.api.domain.UserRepository;
import com.alibaba.scwuser.dao.RegisterDAO;
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

    @Transactional
    @Override
    public int saveRegitser(RegisterDAO registerDAO) {
        TMember tMember = new TMember();
        BeanUtils.copyProperties(registerDAO, tMember);
        return tMemberMapper.insert(tMember);
    }
}
