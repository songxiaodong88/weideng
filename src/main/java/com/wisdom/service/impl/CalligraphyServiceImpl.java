package com.wisdom.service.impl;

import com.wisdom.dao.CalligraphyDao;
import com.wisdom.dto.CalligraphyDTO;
import com.wisdom.service.CalligraphyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CalligraphyServiceImpl implements CalligraphyService {

    @Autowired
    private CalligraphyDao calligraphyDao;

    @Override
    public List<CalligraphyDTO> queryAllCalligraphy() {
        return calligraphyDao.queryAllCalligraphy();
    }
}
