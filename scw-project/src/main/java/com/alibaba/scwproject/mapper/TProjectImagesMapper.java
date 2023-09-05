package com.alibaba.scwproject.mapper;

import com.alibaba.scwproject.bean.TProjectImages;
import com.alibaba.scwproject.bean.TProjectImagesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TProjectImagesMapper {
    long countByExample(TProjectImagesExample example);

    int deleteByExample(TProjectImagesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TProjectImages row);

    int insertSelective(TProjectImages row);

    List<TProjectImages> selectByExample(TProjectImagesExample example);

    TProjectImages selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") TProjectImages row, @Param("example") TProjectImagesExample example);

    int updateByExample(@Param("row") TProjectImages row, @Param("example") TProjectImagesExample example);

    int updateByPrimaryKeySelective(TProjectImages row);

    int updateByPrimaryKey(TProjectImages row);
}