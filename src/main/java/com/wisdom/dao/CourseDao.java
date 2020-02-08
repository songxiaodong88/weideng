package com.wisdom.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wisdom.entity.CourseEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Dao
 *
 * @author 8888
 * @date 2020-01-18 17:55:48
 */
@Mapper
public interface CourseDao extends BaseMapper<CourseEntity> {

    /**
     * 查询所有列表
     *
     * @param params 查询参数
     * @return List
     */
    List<CourseEntity> queryAll(@Param("params") Map<String, Object> params);

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
