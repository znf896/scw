package com.alibaba.scwuser.mapper;

import com.alibaba.scwuser.entity.TMessage;
import com.alibaba.scwuser.entity.TMessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TMessageMapper {
    long countByExample(TMessageExample example);

    int deleteByExample(TMessageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TMessage row);

    int insertSelective(TMessage row);

    List<TMessage> selectByExample(TMessageExample example);

    TMessage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") TMessage row, @Param("example") TMessageExample example);

    int updateByExample(@Param("row") TMessage row, @Param("example") TMessageExample example);

    int updateByPrimaryKeySelective(TMessage row);

    int updateByPrimaryKey(TMessage row);
}