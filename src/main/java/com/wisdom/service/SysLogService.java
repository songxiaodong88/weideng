package com.wisdom.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wisdom.entity.SysLogEntity;

import java.util.Map;

/**
 * 系统日志
 */
public interface SysLogService extends IService<SysLogEntity> {

    /**
     * 分页查询
     *
     * @param params 查询参数
     * @return IPage
     */
    IPage queryPage(Map<String, Object> params);
}
