package com.wisdom.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wisdom.entity.StudentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentDao extends BaseMapper<StudentEntity> {

    /**
     * 查询所有列表
     *
     * @param params 查询参数
     * @return List
     */
    List<StudentEntity> queryAll(@Param("params") Map<String, Object> params);

    /**
     * 自定义分页查询
     *
     * @param page   分页参数
     * @param params 查询参数
     * @return List
     */
    List<StudentEntity> selectStudentPage(IPage page, @Param("params") Map<String, Object> params);

    //  查询所有学生列表
    List<StudentEntity> queryAllStudent();

    //    查询学生ID
    Integer selectStuIDBySname(@Param("sname") String sname);

    //  根据ID查看学生详情
    StudentEntity detailStudentBySid(@Param("sid") Integer sid);

    //  查询所有退学学生信息
    List<StudentEntity> queryAllStudentByTuiXue();

    //  删除学生信息
    Integer updStudentOfStatus(Integer sid);
}
