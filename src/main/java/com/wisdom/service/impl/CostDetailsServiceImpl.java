package com.wisdom.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wisdom.dao.CostDetailsDao;
import com.wisdom.dto.CostDetailsDTO;
import com.wisdom.entity.CostDetailsEntity;
import com.wisdom.service.CostDetailsService;
import com.wisdom.utils.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author 8888
 * @date 2020-01-19 00:05:04
 */
@Service("costDetailsService")
public class CostDetailsServiceImpl extends ServiceImpl<CostDetailsDao, CostDetailsEntity> implements CostDetailsService {

    @Override
    public List<CostDetailsEntity> queryAll(Map<String, Object> params) {
        return baseMapper.queryAll(params);
    }

    @Override
    public Page queryPage(Map<String, Object> params) {
        //排序
        params.put("sidx", "T.cdId");
        params.put("asc", false);
        Page<CostDetailsEntity> page = new Query<CostDetailsEntity>(params).getPage();
        return page.setRecords(baseMapper.selectCostDetailsPage(page, params));
    }

    @Override
    public boolean add(CostDetailsEntity costDetails) {
        return this.save(costDetails);
    }

    @Override
    public boolean update(CostDetailsEntity costDetails) {
        return this.updateById(costDetails);
    }

    @Override
    public boolean delete(Integer cdId) {
        return this.removeById(cdId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBatch(Integer[] cdIds) {
        return this.removeByIds(Arrays.asList(cdIds));
    }

    @Override
    public CostDetailsDTO detailCostByCdId(Integer cdId) {
        return baseMapper.detailCostByCdId(cdId);
    }

    @Override
    public List<CostDetailsEntity> queryCostDetailsByStuId(Integer stuId) {
        return baseMapper.queryCostDetailsByStuId(stuId);
    }
}