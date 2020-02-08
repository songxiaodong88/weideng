package com.wisdom.service.impl;

import com.wisdom.dao.AoShuDao;
import com.wisdom.dto.AoShuZhangDTO;
import com.wisdom.dto.AoShuZhaoDTO;
import com.wisdom.service.AoShuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AoShuServiceImpl implements AoShuService {

    @Autowired
    private AoShuDao aoShuDao;

    @Override
    public List<AoShuZhangDTO> queryAllAoShuZhang() {
        return aoShuDao.queryAllAoShuZhang();
    }

    @Override
    public List<AoShuZhaoDTO> queryAllAoShuZhao() {
        return aoShuDao.queryAllAoShuZhao();
    }
}
