package com.wisdom.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wisdom.dao.ClassesDao;
import com.wisdom.entity.ClassesEntity;
import com.wisdom.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("classes")
public class ClassesServiceImpl extends ServiceImpl<ClassesDao, ClassesEntity> implements ClassesService {

   /* @Resource
    private ClassesDao classesDao;*/

    @Override
    public Integer selectClassIDByClassName(String cname, String schooltime) {
        return baseMapper.selectClassIDByClassName(cname,schooltime);
    }

    @Override
    public List<ClassesEntity> queryAllClasses() {
        return baseMapper.queryAllClasses();
    }

    @Override
    public List<ClassesEntity> queryClassByCourseId(List<Integer> courseId) {
        return baseMapper.queryClassByCourseId(courseId);
    }

    @Override
    public Integer queryCourseIdByClassId(Integer classId) {
        return baseMapper.queryCourseIdByClassId(classId);
    }

    @Override
    public boolean update(ClassesEntity classesEntity) {
        return this.updateById(classesEntity);
    }

    @Override
    public ClassesEntity detailClassesByClassId(Integer classId) {
        return baseMapper.detailClassesByClassId(classId);
    }

}
