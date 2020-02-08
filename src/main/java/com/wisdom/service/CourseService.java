package com.wisdom.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wisdom.entity.CourseEntity;
import com.wisdom.entity.NurseryClassEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;

import java.io.Serializable;
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

    /**
     * 根据主键更新
     *
     * @param courseEntity
     * @return 更新结果
     */
    boolean update(CourseEntity courseEntity);

    //  根据主键查询详情
    CourseEntity getById(Serializable id);

    /**
     * 自定义分页查询
     *
     * @param page   分页参数
     * @param params 查询参数
     * @return List
     */
    List<CourseEntity> selectCoursePage(IPage page, @Param("params") Map<String, Object> params);

    //  根据课程名称查询课程ID
    List<Integer> selectCidByCname(@Param("cname") String cname);
}
