package com.wisdom.service.impl;

import com.wisdom.dao.StudentInSchoolDao;
import com.wisdom.dto.StudentInSchoolDTO;
import com.wisdom.service.StudentInSchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentInSchoolServiceImpl implements StudentInSchoolService {

    @Autowired
    private StudentInSchoolDao studentInSchoolDao;

    @Override
    public List<StudentInSchoolDTO> queryAllStudentInfo() {
        return studentInSchoolDao.queryAllStudentInfo();
    }
}
