package com.wisdom.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wisdom.entity.ClassesEntity;
import com.wisdom.entity.CourseEntity;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

public interface ClassesService extends IService<ClassesEntity> {

    //    根据班级名称查询班级ID
    Integer selectClassIDByClassName(String cname, String schooltime);

    //    查询所有班级信息
    List<ClassesEntity> queryAllUsers();

    //  根据课程ID查询班级信息
    List<ClassesEntity> queryClassByCourseId(List<Integer> courseId);

    //  根据班级ID查询课程ID
    Integer queryCourseIdByClassId(Integer classId);

    /**
     * 根据主键更新
     *
     * @param classesEntity
     * @return 更新结果
     */
    boolean update(ClassesEntity classesEntity);

    //  根据主键查询详情
    ClassesEntity getById(Serializable id);

}
