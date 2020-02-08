package com.wisdom.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wisdom.entity.OilPaintingEntity;

import java.util.List;
import java.util.Map;

/**
 * Service接口
 *
 * @author 8888
 * @date 2020-01-26 17:35:07
 */
public interface OilPaintingService extends IService<OilPaintingEntity> {

    /**
     * 查询所有列表
     *
     * @param params 查询参数
     * @return List
     */
    List<OilPaintingEntity> queryAll(Map<String, Object> params);

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
     * @param oilPainting
     * @return 新增结果
     */
    boolean add(OilPaintingEntity oilPainting);

    /**
     * 根据主键更新
     *
     * @param oilPainting
     * @return 更新结果
     */
    boolean update(OilPaintingEntity oilPainting);

    /**
     * 根据主键删除
     *
     * @param opId opId
     * @return 删除结果
     */
    boolean delete(Integer opId);

    /**
     * 根据主键批量删除
     *
     * @param opIds opIds
     * @return 删除结果
     */
    boolean deleteBatch(Integer[] opIds);
}
