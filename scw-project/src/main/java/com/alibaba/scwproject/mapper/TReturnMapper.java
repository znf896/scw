package com.alibaba.scwproject.mapper;

import com.alibaba.scwproject.bean.TReturn;
import com.alibaba.scwproject.bean.TReturnExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TReturnMapper {
    long countByExample(TReturnExample example);

    int deleteByExample(TReturnExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TReturn row);

    int insertSelective(TReturn row);

    List<TReturn> selectByExample(TReturnExample example);

    TReturn selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") TReturn row, @Param("example") TReturnExample example);

    int updateByExample(@Param("row") TReturn row, @Param("example") TReturnExample example);

    int updateByPrimaryKeySelective(TReturn row);

    int updateByPrimaryKey(TReturn row);
}