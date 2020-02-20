package com.wisdom.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wisdom.entity.ClassesEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClassesDao extends BaseMapper<ClassesEntity> {

    //    根据班级名称查询班级ID
    Integer selectClassIDByClassName(@Param("cname") String cname, @Param("schooltime") String schooltime);

    //    查询所有班级信息
    List<ClassesEntity> queryAllClasses();

    //  根据课程ID查询班级信息
    List<ClassesEntity> queryClassByCourseId(List<Integer> course_id);

    //  根据班级ID查询课程ID
    Integer queryCourseIdByClassId(@Param("classId") Integer classId);

    //  根据班级ID查看班级详情
    ClassesEntity detailClassesByClassId(@Param("classId") Integer classId);

}
