package com.wisdom.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wisdom.entity.StudentEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Service接口
 *
 * @author 8888
 * @date 2020-01-18 23:38:16
 */
public interface StudentService extends IService<StudentEntity> {

    /**
     * 查询所有列表
     *
     * @param params 查询参数
     * @return List
     */
    List<StudentEntity> queryAll(Map<String, Object> params);

    /**
     * 分页查询
     *
     * @param params 查询参数
     * @return Page
     */
    Page queryPage(Map<String, Object> params);

    /**
     * 新增
     *
     * @param student
     * @return 新增结果
     */
    boolean add(StudentEntity student);

    /**
     * 根据主键更新
     *
     * @param student
     * @return 更新结果
     */
    boolean update(StudentEntity student);

    /**
     * 根据主键删除
     *
     * @param sid sid
     * @return 删除结果
     */
    boolean delete(Integer sid);

    /**
     * 根据主键批量删除
     *
     * @param sids sids
     * @return 删除结果
     */
    boolean deleteBatch(Integer[] sids);

    //    查询学生ID
    Integer selectStuIDBySname(String sname);

    //  根据ID查看学生详情
    StudentEntity detailStudentBySid(@Param("sid") Integer sid);

    //  查询所有退学学生信息
    List<StudentEntity> queryAllStudentByTuiXue();

    //  删除学生信息
    Integer updStudentOfStatus(Integer sid);
}
