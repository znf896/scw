package com.alibaba.scwproject.mapper;

import com.alibaba.scwproject.bean.TProjectType;
import com.alibaba.scwproject.bean.TProjectTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TProjectTypeMapper {
    long countByExample(TProjectTypeExample example);

    int deleteByExample(TProjectTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TProjectType row);

    int insertSelective(TProjectType row);

    List<TProjectType> selectByExample(TProjectTypeExample example);

    TProjectType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") TProjectType row, @Param("example") TProjectTypeExample example);

    int updateByExample(@Param("row") TProjectType row, @Param("example") TProjectTypeExample example);

    int updateByPrimaryKeySelective(TProjectType row);

    int updateByPrimaryKey(TProjectType row);
}