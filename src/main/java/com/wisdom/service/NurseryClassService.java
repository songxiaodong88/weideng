package com.wisdom.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wisdom.dto.NurseryClassDTO;
import com.wisdom.entity.CostDetailsEntity;
import com.wisdom.entity.NurseryClassEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NurseryClassService extends IService<NurseryClassEntity> {

    /**
     * 根据主键更新
     *
     * @param nurseryClassEntity
     * @return 更新结果
     */
    boolean update(NurseryClassEntity nurseryClassEntity);

    //    查询所有幼小班信息
    List<NurseryClassDTO> queryAllNurseryClass();

    //  根据学生ID查询所有关联的课程信息
    NurseryClassEntity queryNurseyClassByStuId(Integer stuId);

    //  根据ID查询详细信息
    NurseryClassDTO detailNurseryClassByNcId(Integer courseId,Integer ncId);

    //  删除课程信息，将状态修改为删除
    Integer updNurseryClassStatusByNcId(Integer ncId);

    //  修改幼儿班/幼小衔接班信息
    Integer updateNurseryClass(NurseryClassEntity nurseryClassEntity);
}
