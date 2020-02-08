package com.wisdom.dao;

import com.wisdom.dto.StudentInSchoolDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentInSchoolDao {

    //    查询所有在校学员信息
    List<StudentInSchoolDTO> queryAllStudentInfo();
}
