package com.alibaba.scwuser.mapper;

import com.alibaba.scwuser.entity.TMemberCert;
import com.alibaba.scwuser.entity.TMemberCertExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TMemberCertMapper {
    long countByExample(TMemberCertExample example);

    int deleteByExample(TMemberCertExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TMemberCert row);

    int insertSelective(TMemberCert row);

    List<TMemberCert> selectByExample(TMemberCertExample example);

    TMemberCert selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") TMemberCert row, @Param("example") TMemberCertExample example);

    int updateByExample(@Param("row") TMemberCert row, @Param("example") TMemberCertExample example);

    int updateByPrimaryKeySelective(TMemberCert row);

    int updateByPrimaryKey(TMemberCert row);
}