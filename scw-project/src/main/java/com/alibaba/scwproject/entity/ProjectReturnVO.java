package com.alibaba.scwproject.entity;

import com.alibaba.scwproject.bean.TReturn;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Data
public class ProjectReturnVO extends BaseVO {
    /**
     * 项目的临时token
     */
    private String projectToken;

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


    public static boolean compareRedisVO(ProjectReturnVO ProjectReturnVO, ProjectRedisVO projectRedisVO) {
        if (!StringUtils.equals(ProjectReturnVO.getPhone(), projectRedisVO.getPhone())
                || !StringUtils.equals(ProjectReturnVO.getProjectToken(), projectRedisVO.getProjectToken())
                || !StringUtils.equals(ProjectReturnVO.getAccessToken(), projectRedisVO.getAccessToken())) {
            return false;
        }
        return true;
    }

    public static boolean isLogin(ProjectReturnVO ProjectReturnVO) {
        if (org.apache.commons.lang.StringUtils.isBlank(ProjectReturnVO.getPhone())
                || org.apache.commons.lang.StringUtils.isBlank(ProjectReturnVO.getAccessToken())
                || org.apache.commons.lang.StringUtils.isBlank(ProjectReturnVO.getProjectToken())) {
            return false;
        }
        return true;
    }

    public static List<TReturn> copyTReturn(List<ProjectReturnVO> projectReturnVOList) {
        List<TReturn> tReturnList = new ArrayList<>();
        try {
            projectReturnVOList.forEach(vo -> {
                TReturn tReturn = new TReturn();
                BeanUtils.copyProperties(vo, tReturn);
                tReturnList.add(tReturn);
            });
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
        return tReturnList;
    }
}
