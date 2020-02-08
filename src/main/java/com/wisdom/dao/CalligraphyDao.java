package com.wisdom.dao;

import com.wisdom.dto.CalligraphyDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CalligraphyDao {

    //    查询所有书法部信息
    List<CalligraphyDTO> queryAllCalligraphy();
}
