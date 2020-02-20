package com.wisdom.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wisdom.dao.CourseDao;
import com.wisdom.service.CourseService;
import com.wisdom.entity.CourseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CourseServiceImpl extends ServiceImpl<CourseDao, CourseEntity> implements CourseService {

    @Override
    public List<CourseEntity> queryAll(Map<String, Object> params) {
        return baseMapper.queryAll(params);
    }

    @Override
    public List<CourseEntity> queryAllCourse(){
        return baseMapper.queryAllCourse();
    }

    @Override
    public boolean update(CourseEntity courseEntity) {
        return this.updateById(courseEntity);
    }

    @Override
    public List<CourseEntity> selectCoursePage(IPage page, Map<String, Object> params) {
        return baseMapper.selectCoursePage(page,params);
    }

    @Override
    public List<Integer> selectCidByCname(String cname) {
        return baseMapper.selectCidByCname(cname);
    }

    @Override
    public CourseEntity detailCourseByCid(Integer cid) {
        return baseMapper.detailCourseByCid(cid);
    }
}
