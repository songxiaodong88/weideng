package com.wisdom.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wisdom.entity.SysLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统日志Dao
 */
@Mapper
public interface SysLogDao extends BaseMapper<SysLogEntity> {

}
