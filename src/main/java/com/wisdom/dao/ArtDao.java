package com.wisdom.dao;

import com.wisdom.dto.ArtDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArtDao {

    //    查询所有绘画部信息
    List<ArtDTO> queryAllArt();
}
