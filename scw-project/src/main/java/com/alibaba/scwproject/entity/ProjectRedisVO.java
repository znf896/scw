package com.alibaba.scwproject.entity;

import com.alibaba.scwproject.bean.TProject;
import com.alibaba.scwproject.bean.TProjectImages;
import com.alibaba.scwproject.bean.TReturn;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProjectRedisVO extends BaseVO {
    private Integer id;
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


    public static TProject convert2TProject(ProjectRedisVO projectRedisVO) {
        TProject tProject = new TProject();
        tProject.setMemberid(projectRedisVO.getMemberid());
        tProject.setMoney((long) (projectRedisVO.getProjectMoney()));
        tProject.setName(projectRedisVO.getProjectName());
        tProject.setDay(projectRedisVO.getCrowdfundingDay());
        return tProject;
    }

    public static TProjectImages convert2TProjectHeadImages(ProjectRedisVO projectRedisVO) {
        TProjectImages tProjectImages = new TProjectImages();
        tProjectImages.setProjectId(projectRedisVO.getId());
        tProjectImages.setImageType((byte) 0);
        tProjectImages.setImageUrl(projectRedisVO.getHeaderImage());
        return tProjectImages;
    }

    public static List<TProjectImages> convert2TProjectDetailImages(ProjectRedisVO projectRedisVO) {
        List<TProjectImages> imageList = new ArrayList<>();
        projectRedisVO.getDetailsImage().forEach(s -> {
            TProjectImages tProjectImages = new TProjectImages();
            tProjectImages.setProjectId(projectRedisVO.getId());
            tProjectImages.setImageUrl(s);
            tProjectImages.setImageType((byte) 1);
            imageList.add(tProjectImages);
        });
        return imageList;
    }

}
