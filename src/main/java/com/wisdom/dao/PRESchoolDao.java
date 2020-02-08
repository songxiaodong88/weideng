package com.wisdom.dao;

import com.wisdom.dto.PRESchoolDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PRESchoolDao {

    //    查询所有幼小衔接班信息
    List<PRESchoolDTO> queryAllPRESchool();
}
