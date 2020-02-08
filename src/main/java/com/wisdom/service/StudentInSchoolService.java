package com.wisdom.service;

import com.wisdom.dto.StudentInSchoolDTO;

import java.util.List;

public interface StudentInSchoolService {

    //    查询所有在校学员信息
    List<StudentInSchoolDTO> queryAllStudentInfo();
}
