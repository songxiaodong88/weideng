package com.wisdom.service.impl;

import com.wisdom.dao.PRESchoolDao;
import com.wisdom.dto.PRESchoolDTO;
import com.wisdom.service.PRESchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PRESchoolServiceImpl implements PRESchoolService {

    @Autowired
    private PRESchoolDao preSchoolDao;

    @Override
    public List<PRESchoolDTO> queryAllPRESchool() {
        return preSchoolDao.queryAllPRESchool();
    }
}
