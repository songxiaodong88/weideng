package com.wisdom.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wisdom.dto.CostDetailsDTO;
import com.wisdom.entity.CostDetailsEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Dao
 *
 * @author 8888
 * @date 2020-01-19 00:05:04
 */
@Mapper
public interface CostDetailsDao extends BaseMapper<CostDetailsEntity> {

    /**
     * 查询所有列表
     *
     * @param params 查询参数
     * @return List
     */
    List<CostDetailsEntity> queryAll(@Param("params") Map<String, Object> params);

    /**
     * 自定义分页查询
     *
     * @param page   分页参数
     * @param params 查询参数
     * @return List
     */
    List<CostDetailsEntity> selectCostDetailsPage(IPage page, @Param("params") Map<String, Object> params);

    //  根据ID查询课程详情
    CostDetailsDTO detailCostByCdId(@Param("cdId") Integer cdId);

    //  根据学生ID查询所有有关课程信息
    List<CostDetailsEntity> queryCostDetailsByStuId(@Param("stuId") Integer stuId);

    //  根据学生ID修改所有有关课程信息的状态
    Integer updCostDetailsOfStatus(@Param("stuId") Integer stuId);
}