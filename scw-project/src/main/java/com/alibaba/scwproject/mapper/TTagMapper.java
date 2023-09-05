package com.alibaba.scwproject.mapper;

import com.alibaba.scwproject.bean.TTag;
import com.alibaba.scwproject.bean.TTagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TTagMapper {
    long countByExample(TTagExample example);

    int deleteByExample(TTagExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TTag row);

    int insertSelective(TTag row);

    List<TTag> selectByExample(TTagExample example);

    TTag selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") TTag row, @Param("example") TTagExample example);

    int updateByExample(@Param("row") TTag row, @Param("example") TTagExample example);

    int updateByPrimaryKeySelective(TTag row);

    int updateByPrimaryKey(TTag row);
}