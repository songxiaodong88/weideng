package com.wisdom.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wisdom.entity.OilPaintingEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Dao
 *
 * @author 8888
 * @date 2020-01-26 17:35:07
 */
@Mapper
public interface OilPaintingDao extends BaseMapper<OilPaintingEntity> {

    /**
     * 查询所有列表
     *
     * @param params 查询参数
     * @return List
     */
    List<OilPaintingEntity> queryAll(@Param("params") Map<String, Object> params);

    /**
     * 自定义分页查询
     *
     * @param page   分页参数
     * @param params 查询参数
     * @return List
     */
    List<OilPaintingEntity> selectOilPaintingPage(IPage page, @Param("params") Map<String, Object> params);
}
