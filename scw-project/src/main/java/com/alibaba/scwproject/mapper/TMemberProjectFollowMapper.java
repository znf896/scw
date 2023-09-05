package com.alibaba.scwproject.mapper;

import com.alibaba.scwproject.bean.TMemberProjectFollow;
import com.alibaba.scwproject.bean.TMemberProjectFollowExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TMemberProjectFollowMapper {
    long countByExample(TMemberProjectFollowExample example);

    int deleteByExample(TMemberProjectFollowExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TMemberProjectFollow row);

    int insertSelective(TMemberProjectFollow row);

    List<TMemberProjectFollow> selectByExample(TMemberProjectFollowExample example);

    TMemberProjectFollow selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") TMemberProjectFollow row, @Param("example") TMemberProjectFollowExample example);

    int updateByExample(@Param("row") TMemberProjectFollow row, @Param("example") TMemberProjectFollowExample example);

    int updateByPrimaryKeySelective(TMemberProjectFollow row);

    int updateByPrimaryKey(TMemberProjectFollow row);
}