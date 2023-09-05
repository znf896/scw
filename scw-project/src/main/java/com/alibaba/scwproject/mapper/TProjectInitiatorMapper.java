package com.alibaba.scwproject.mapper;

import com.alibaba.scwproject.bean.TProjectInitiator;
import com.alibaba.scwproject.bean.TProjectInitiatorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TProjectInitiatorMapper {
    long countByExample(TProjectInitiatorExample example);

    int deleteByExample(TProjectInitiatorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TProjectInitiator row);

    int insertSelective(TProjectInitiator row);

    List<TProjectInitiator> selectByExample(TProjectInitiatorExample example);

    TProjectInitiator selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") TProjectInitiator row, @Param("example") TProjectInitiatorExample example);

    int updateByExample(@Param("row") TProjectInitiator row, @Param("example") TProjectInitiatorExample example);

    int updateByPrimaryKeySelective(TProjectInitiator row);

    int updateByPrimaryKey(TProjectInitiator row);
}