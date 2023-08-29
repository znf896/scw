package com.alibaba.scwuser.mapper;

import com.alibaba.scwuser.entity.TMemberAddress;
import com.alibaba.scwuser.entity.TMemberAddressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TMemberAddressMapper {
    long countByExample(TMemberAddressExample example);

    int deleteByExample(TMemberAddressExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TMemberAddress row);

    int insertSelective(TMemberAddress row);

    List<TMemberAddress> selectByExample(TMemberAddressExample example);

    TMemberAddress selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") TMemberAddress row, @Param("example") TMemberAddressExample example);

    int updateByExample(@Param("row") TMemberAddress row, @Param("example") TMemberAddressExample example);

    int updateByPrimaryKeySelective(TMemberAddress row);

    int updateByPrimaryKey(TMemberAddress row);
}