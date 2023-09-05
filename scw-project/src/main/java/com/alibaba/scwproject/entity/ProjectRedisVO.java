package com.alibaba.scwproject.entity;

import com.alibaba.scwproject.bean.TReturn;
import lombok.Data;

import java.util.List;

@Data
public class ProjectRedisVO extends BaseVO {
    /**
     * 项目的临时token
     */
    private String projectToken;

    /**
     * 会员id
     */
    private Integer memberid;

    /**
     * 项目的分类id
     */
    private List<Integer> typeids;

    /**
     * 项目的标签id
     */
    private List<Integer> tagids;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 项目简介
     */
    private String projectRemark;

    /**
     * 筹资金额
     */
    private Integer projectMoney;

    /**
     * 筹资天数
     */
    private Integer crowdfundingDay;

    /**
     * 项目头部图片
     */
    private String headerImage;

    /**
     * 项目详情图片
     */
    private List<String> detailsImage;

    /**
     * 项目回报
     */
    private List<TReturn> projectReturns;

}
