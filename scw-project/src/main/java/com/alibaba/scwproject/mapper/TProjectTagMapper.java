package com.alibaba.scwproject.mapper;

import com.alibaba.scwproject.bean.TProjectTag;
import com.alibaba.scwproject.bean.TProjectTagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TProjectTagMapper {
    long countByExample(TProjectTagExample example);

    int deleteByExample(TProjectTagExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TProjectTag row);

    int insertSelective(TProjectTag row);

    List<TProjectTag> selectByExample(TProjectTagExample example);

    TProjectTag selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") TProjectTag row, @Param("example") TProjectTagExample example);

    int updateByExample(@Param("row") TProjectTag row, @Param("example") TProjectTagExample example);

    int updateByPrimaryKeySelective(TProjectTag row);

    int updateByPrimaryKey(TProjectTag row);
}