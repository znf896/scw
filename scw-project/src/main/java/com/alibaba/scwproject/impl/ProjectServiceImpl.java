package com.alibaba.scwproject.impl;

import com.alibaba.scwproject.bean.TProject;
import com.alibaba.scwproject.bean.TProjectImages;
import com.alibaba.scwproject.entity.ProjectRedisVO;
import com.alibaba.scwproject.mapper.TProjectImagesMapper;
import com.alibaba.scwproject.mapper.TProjectMapper;
import com.alibaba.scwproject.mapper.TReturnMapper;
import com.alibaba.scwproject.service.ProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service
public class ProjectServiceImpl implements ProjectService {
    @Resource
    TProjectMapper tProjectMapper;
    @Resource
    TProjectImagesMapper tProjectImagesMapper;
    @Resource
    TReturnMapper tReturnMapper;

    @Override
    public Integer saveProjectInfo(ProjectRedisVO projectRedisVO) {
        //保存项目基本信息
        TProject tProject = ProjectRedisVO.convert2TProject(projectRedisVO);
        tProjectMapper.insert(tProject);
        projectRedisVO.setId(tProject.getId());
        //保存头图
        TProjectImages tProjectHeadImages = ProjectRedisVO.convert2TProjectHeadImages(projectRedisVO);
        tProjectImagesMapper.insert(tProjectHeadImages);
        //保存详情图
        List<TProjectImages> tProjectImages = ProjectRedisVO.convert2TProjectDetailImages(projectRedisVO);
        tProjectImages.forEach(vo -> tProjectImagesMapper.insert(vo));

        //保存回报
        projectRedisVO.getProjectReturns().forEach(vo -> {
            vo.setProjectid(projectRedisVO.getId());
            tReturnMapper.insert(vo);
        });
        //todo:保存项目分类
        return 1;
    }
}
