package com.alibaba.scwuser.mapper;

import com.alibaba.scwuser.dao.UserDAO;
import com.alibaba.scwuser.entity.TMember;
import com.alibaba.scwuser.entity.TMemberExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TMemberMapper {
    long countByExample(TMemberExample example);

    int deleteByExample(TMemberExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TMember row);

    int insertSelective(TMember row);

    List<TMember> selectByExample(TMemberExample example);

    TMember selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") TMember row, @Param("example") TMemberExample example);

    int updateByExample(@Param("row") TMember row, @Param("example") TMemberExample example);

    int updateByPrimaryKeySelective(TMember row);

    int updateByPrimaryKey(TMember row);

    String findByEmail(String email);

    TMember selectByUsername(UserDAO userDAO);

}