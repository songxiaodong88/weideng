package com.wisdom.dao;

import com.wisdom.dto.EnglishDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EnglishDao {

    // 查询英语部信息
    List<EnglishDTO> queryAllEnglish();
}
