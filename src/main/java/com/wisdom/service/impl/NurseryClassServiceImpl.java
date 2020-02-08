package com.wisdom.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wisdom.dao.NurseryClassDao;
import com.wisdom.dto.NurseryClassDTO;
import com.wisdom.entity.NurseryClassEntity;
import com.wisdom.service.NurseryClassService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NurseryClassServiceImpl extends ServiceImpl<NurseryClassDao,NurseryClassEntity> implements NurseryClassService {


    @Override
    public boolean update(NurseryClassEntity nurseryClassEntity) {
        return this.updateById(nurseryClassEntity);
    }

    @Override
    public List<NurseryClassDTO> queryAllNurseryClass() {
        return baseMapper.queryAllNurseryClass();
    }

    @Override
    public NurseryClassEntity queryNurseyClassByStuId(Integer stuId) {
        return baseMapper.queryNurseyClassByStuId(stuId);
    }

    @Override
    public NurseryClassDTO detailNurseryClassByNcId(Integer courseId, Integer ncId) {
        return baseMapper.detailNurseryClassByNcId(courseId,ncId);
    }

    @Override
    public Integer updNurseryClassStatusByNcId(Integer ncId) {
        return baseMapper.updNurseryClassStatusByNcId(ncId);
    }

    @Override
    public Integer updateNurseryClass(NurseryClassEntity nurseryClassEntity) {
        return baseMapper.updateNurseryClass(nurseryClassEntity);
    }
}
