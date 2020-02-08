package com.wisdom.controller;

import com.wisdom.dto.PRESchoolDTO;
import com.wisdom.service.PRESchoolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 幼小衔接班
 */
@Controller
@RequestMapping("preSchool")
public class PRESchoolController {

    @Autowired
    private PRESchoolService preSchoolService;

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(PRESchoolController.class);

    //  查询所有幼小衔接班信息
    @RequestMapping(value="queryAllPRESchool")
    public String queryAllPRESchool(Model model){
        List<PRESchoolDTO> preList = preSchoolService.queryAllPRESchool();
        model.addAttribute("preList", preList);
        model.addAttribute("preTotal", preList.get(0).getSumTotal());
        return "preSchool/PRESchoolList";
    }




}
