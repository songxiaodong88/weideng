package com.wisdom.service.impl;

import com.wisdom.dao.WuShuDao;
import com.wisdom.dto.WuShuDTO;
import com.wisdom.service.WuShuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WuShuServiceImpl implements WuShuService {

    @Autowired
    private WuShuDao wuShuDao;

    @Override
    public List<WuShuDTO> queryAllWuShu() {
        return wuShuDao.queryAllWuShu();
    }
}
