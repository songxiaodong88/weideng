package com.wisdom.service.impl;

import com.wisdom.dao.EnglishDao;
import com.wisdom.dto.EnglishDTO;
import com.wisdom.service.EnglishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EnglishServiceImpl implements EnglishService {

    @Autowired
    private EnglishDao englishDao;

    @Override
    public List<EnglishDTO> queryAllEnglish() {
        return englishDao.queryAllEnglish();
    }
}
