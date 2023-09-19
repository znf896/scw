package com.alibaba.scwproject.entity;

import lombok.Data;
import org.apache.commons.codec.binary.StringUtils;

import java.util.List;

@Data
public class ProjectBaseInfoVO extends BaseVO {
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


    public static boolean compareRedisVO(ProjectBaseInfoVO projectBaseInfoVO, ProjectRedisVO projectRedisVO) {
        if (!StringUtils.equals(projectBaseInfoVO.getPhone(), projectRedisVO.getPhone())
                || !StringUtils.equals(projectBaseInfoVO.getProjectToken(), projectRedisVO.getProjectToken())
                || !StringUtils.equals(projectBaseInfoVO.getAccessToken(), projectRedisVO.getAccessToken())) {
            return false;

        }
        return true;
    }

    public static boolean isLogin(ProjectBaseInfoVO projectBaseInfoVO) {
        if (org.apache.commons.lang.StringUtils.isBlank(projectBaseInfoVO.getPhone())
                || org.apache.commons.lang.StringUtils.isBlank(projectBaseInfoVO.getAccessToken())
                || org.apache.commons.lang.StringUtils.isBlank(projectBaseInfoVO.getProjectToken())) {
            return false;
        }
        return true;
    }
}
