package com.wisdom.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wisdom.entity.CourseEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CourseService extends IService<CourseEntity> {

    /**
     * 查询所有列表
     *
     * @param params 查询参数
     * @return List
     */
    List<CourseEntity> queryAll(@Param("params") Map<String, Object> params);

    //  查询所有课程
    List<CourseEntity> queryAllCourse();

    /**
     * 根据主键更新
     *
     * @param courseEntity
     * @return 更新结果
     */
    boolean update(CourseEntity courseEntity);

    /**
     * 自定义分页查询
     *
     * @param page   分页参数
     * @param params 查询参数
     * @return List
     */
    List<CourseEntity> selectCoursePage(IPage page, @Param("params") Map<String, Object> params);

    //  根据课程名称查询课程ID
    List<Integer> selectCidByCname(String cname);

    //    根据课程ID查看课程信息
    CourseEntity detailCourseByCid(Integer cid);

}
