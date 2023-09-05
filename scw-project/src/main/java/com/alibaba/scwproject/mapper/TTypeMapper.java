package com.alibaba.scwproject.mapper;

import com.alibaba.scwproject.bean.TType;
import com.alibaba.scwproject.bean.TTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TTypeMapper {
    long countByExample(TTypeExample example);

    int deleteByExample(TTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TType row);

    int insertSelective(TType row);

    List<TType> selectByExample(TTypeExample example);

    TType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") TType row, @Param("example") TTypeExample example);

    int updateByExample(@Param("row") TType row, @Param("example") TTypeExample example);

    int updateByPrimaryKeySelective(TType row);

    int updateByPrimaryKey(TType row);
}