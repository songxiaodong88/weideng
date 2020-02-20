package com.wisdom.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wisdom.entity.SysConfigEntity;

import java.util.Map;

/**
 * 系统配置信息
 */
public interface SysConfigService extends IService<SysConfigEntity> {

    /**
     * 分页查询
     *
     * @param params 查询参数
     * @return Page
     */
    Page queryPage(Map<String, Object> params);

    /**
     * 保存配置信息
     *
     * @param config config
     */
    void add(SysConfigEntity config);

    /**
     * 更新配置信息
     *
     * @param config config
     */
    void update(SysConfigEntity config);

    /**
     * 根据key，更新value
     *
     * @param key   key
     * @param value value
     */
    void updateValueByKey(String key, String value);

    /**
     * 删除配置信息
     *
     * @param ids ids
     */
    void deleteBatch(String[] ids);

    /**
     * 根据key，获取配置的value值
     *
     * @param key key
     * @return String
     */
    String getValue(String key);

    /**
     * 根据key，获取配置的value值
     *
     * @param key          key
     * @param defaultValue 缺省值
     */
    String getValue(String key, String defaultValue);

    /**
     * 根据key，获取value的Object对象
     *
     * @param key   key
     * @param clazz clazz
     * @param <T>   <T>
     * @return <T>
     */
    <T> T getConfigObject(String key, Class<T> clazz);

}
