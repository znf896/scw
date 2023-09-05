package com.alibaba.scwproject.mapper;

import com.alibaba.scwproject.bean.TProject;
import com.alibaba.scwproject.bean.TProjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TProjectMapper {
    long countByExample(TProjectExample example);

    int deleteByExample(TProjectExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TProject row);

    int insertSelective(TProject row);

    List<TProject> selectByExample(TProjectExample example);

    TProject selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") TProject row, @Param("example") TProjectExample example);

    int updateByExample(@Param("row") TProject row, @Param("example") TProjectExample example);

    int updateByPrimaryKeySelective(TProject row);

    int updateByPrimaryKey(TProject row);
}