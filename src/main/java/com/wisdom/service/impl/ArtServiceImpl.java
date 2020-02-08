package com.wisdom.service.impl;

import com.wisdom.dao.ArtDao;
import com.wisdom.dto.ArtDTO;
import com.wisdom.service.ArtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ArtServiceImpl implements ArtService {

    @Autowired
    private ArtDao artDao;

    @Override
    public List<ArtDTO> queryAllArt() {
        return artDao.queryAllArt();
    }
}
