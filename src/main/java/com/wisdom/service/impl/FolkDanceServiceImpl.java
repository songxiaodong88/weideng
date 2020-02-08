package com.wisdom.service.impl;

import com.wisdom.dao.FolkDanceDao;
import com.wisdom.dto.FolkDanceDTO;
import com.wisdom.service.FolkDanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FolkDanceServiceImpl implements FolkDanceService {

    @Autowired
    private FolkDanceDao folkDanceDao;

    @Override
    public List<FolkDanceDTO> queryAllFolkDance_chen() {
        return folkDanceDao.queryAllFolkDance_chen();
    }

    @Override
    public List<FolkDanceDTO> queryAllFolkDance_yin() {
        return folkDanceDao.queryAllFolkDance_yin();
    }

    @Override
    public List<FolkDanceDTO> queryAllFolkDance_PRE() {
        return folkDanceDao.queryAllFolkDance_PRE();
    }
}
