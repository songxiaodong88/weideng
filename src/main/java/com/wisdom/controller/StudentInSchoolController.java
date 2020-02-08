package com.wisdom.controller;

import com.wisdom.dto.StudentInSchoolDTO;
import com.wisdom.service.StudentInSchoolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 所有在校学员信息
 */
@Controller
@RequestMapping("studentInSchool")
public class StudentInSchoolController {

    @Autowired
    private StudentInSchoolService studentInSchoolService;

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(StudentInSchoolController.class);

    //  查询所有幼小衔接班信息
    @RequestMapping(value="queryAllStudentInSchool")
    public String queryAllStudentInSchool(Model model){
        List<StudentInSchoolDTO> stuList = studentInSchoolService.queryAllStudentInfo();
//        for (StudentInSchoolDTO student : stuList) {
//            System.out.println(student.getSname());
//        }
        model.addAttribute("sum_stu", stuList.get(0).getSumTotal());
        model.addAttribute("stuAll", stuList);
        return "studentInSchool/studentInSchool";
    }




}
