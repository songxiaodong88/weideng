package com.wisdom.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wisdom.dao.OilPaintingDao;
import com.wisdom.entity.OilPaintingEntity;
import com.wisdom.service.OilPaintingService;
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
 * @date 2020-01-26 17:35:07
 */
@Service("oilPaintingService")
public class OilPaintingServiceImpl extends ServiceImpl<OilPaintingDao, OilPaintingEntity> implements OilPaintingService {

    @Override
    public List<OilPaintingEntity> queryAll(Map<String, Object> params) {
        return baseMapper.queryAll(params);
    }

    @Override
    public Page queryPage(Map<String, Object> params) {
        //排序
        params.put("sidx", "T.opId");
        params.put("asc", false);
        Page<OilPaintingEntity> page = new Query<OilPaintingEntity>(params).getPage();
        return page.setRecords(baseMapper.selectOilPaintingPage(page, params));
    }

    @Override
    public boolean add(OilPaintingEntity oilPainting) {
        return this.save(oilPainting);
    }

    @Override
    public boolean update(OilPaintingEntity oilPainting) {
        return this.updateById(oilPainting);
    }

    @Override
    public boolean delete(Integer opId) {
        return this.removeById(opId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBatch(Integer[] opIds) {
        return this.removeByIds(Arrays.asList(opIds));
    }
}
