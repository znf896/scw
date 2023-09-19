package com.alibaba.scwproject.entity;

import lombok.Data;

@Data
public class ProjectReturnVO extends BaseVO {
    /**
     * 回报类型 0:实物回报 1:虚拟物品回报
     */
    private String type;

    /**
     * 支持金额
     */
    private Integer supportmoney;

    /**
     * 回报内容
     */
    private String content;

    /**
     * 回报数量
     */
    private Integer count;

    /**
     * 单笔限购 0:不限购 1:限购
     */
    private Integer signalpurchase;

    /**
     * 限购数量
     */
    private Integer purchase;

    /**
     * 运费 0：包邮
     */
    private Integer freight;

    /**
     * 发票 0:不可开发票 1:可开发票
     */
    private String invoice;

    /**
     * 回报时间
     */
    private Integer rtndate;

}
