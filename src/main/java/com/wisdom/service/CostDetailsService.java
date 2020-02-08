package com.wisdom.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wisdom.dto.CostDetailsDTO;
import com.wisdom.entity.CostDetailsEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Service接口
 *
 * @author 8888
 * @date 2020-01-19 00:05:04
 */
public interface CostDetailsService extends IService<CostDetailsEntity> {

    /**
     * 查询所有列表
     *
     * @param params 查询参数
     * @return List
     */
    List<CostDetailsEntity> queryAll(Map<String, Object> params);

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
     * @param costDetails
     * @return 新增结果
     */
    boolean add(CostDetailsEntity costDetails);

    /**
     * 根据主键更新
     *
     * @param costDetails
     * @return 更新结果
     */
    boolean update(CostDetailsEntity costDetails);

    /**
     * 根据主键删除
     *
     * @param cdId cdId
     * @return 删除结果
     */
    boolean delete(Integer cdId);

    /**
     * 根据主键批量删除
     *
     * @param cdIds cdIds
     * @return 删除结果
     */
    boolean deleteBatch(Integer[] cdIds);

    //  根据ID查询课程详情
    CostDetailsDTO detailCostByCdId(Integer cdId);

    //  根据学生ID查询所有有关课程信息
    List<CostDetailsEntity> queryCostDetailsByStuId(@Param("stuId") Integer stuId);
}