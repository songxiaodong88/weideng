package com.wisdom.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wisdom.entity.SysConfigEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 系统配置信息
 */
@Mapper
public interface SysConfigDao extends BaseMapper<SysConfigEntity> {

    /**
     * 根据key，查询value
     *
     * @param paramKey key
     * @return SysConfigEntity
     */
    SysConfigEntity queryByKey(String paramKey);

    /**
     * 根据key，更新value
     *
     * @param paramKey   key
     * @param paramValue value
     * @return int
     */
    int updateValueByKey(@Param("paramKey") String paramKey, @Param("paramValue") String paramValue);
}
