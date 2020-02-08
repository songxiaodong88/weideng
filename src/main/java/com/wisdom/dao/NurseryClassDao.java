package com.wisdom.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wisdom.dto.NurseryClassDTO;
import com.wisdom.entity.NurseryClassEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NurseryClassDao extends BaseMapper<NurseryClassEntity> {

    //    查询所有幼小班信息
    List<NurseryClassDTO> queryAllNurseryClass();

    //  根据学生ID查询所有关联的课程信息
    NurseryClassEntity queryNurseyClassByStuId(@Param("stuId") Integer stuId);

    //  根据ID查询详细信息
    NurseryClassDTO detailNurseryClassByNcId(@Param("courseId") Integer courseId, @Param("ncId") Integer ncId);

    //  删除课程信息，将状态修改为删除
    Integer updNurseryClassStatusByNcId(@Param("ncId") Integer ncId);

    //  修改幼儿班/幼小衔接班信息
    Integer updateNurseryClass(NurseryClassEntity nurseryClassEntity);
}
