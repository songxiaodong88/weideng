package com.wisdom.controller;

import com.wisdom.common.annotation.SysLog;
import com.wisdom.entity.ClassesEntity;
import com.wisdom.entity.CourseEntity;
import com.wisdom.service.ClassesService;
import com.wisdom.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("classes")
public class ClassesController {

    @Autowired
    private ClassesService classesService;
    @Autowired
    private CourseService courseService;

    //  查询所有班级信息
    @RequestMapping(value="queryAllClasses")
    public String queryAllClasses(Model model){
        List<ClassesEntity> cList = classesService.queryAllUsers();
        model.addAttribute("classes", cList);
        return "infoManagement/classesList";
    }

    //  去添加班级信息页面
    @GetMapping(value="toAddClasses")
    public String toAddClasses(Model model, Map<String,Object> params){
        model.addAttribute("courseList", courseService.queryAll(params));
        return "clazz/addClazz";
    }

    //  执行添加班级信息操作
    @SysLog("添加班级信息")
    @RequestMapping(value="doAddClasses")
    public String doAddClasses(ClassesEntity classesEntity){
        classesEntity.setClassId(0);
        if (classesService.save(classesEntity)) {
            return "redirect:/classes/queryAllClasses";
        }else{
            return "redirect:/classes/toAddClasses";
        }
    }

    //  去修改班级信息页面
    @RequestMapping(value = "toUpdateClasses")
    public String toUpdateClasses(Integer classId,Model model,Map<String,Object>params) {
        model.addAttribute("courseList", courseService.queryAll(params));
        model.addAttribute("clazz", classesService.getById(classId));
        return "clazz/updateClazz";
    }

    //  执行修改班级信息操作
    @SysLog("修改班级信息")
    @RequestMapping(value="doUpdateClasses")
    public String doUpdateClasses(ClassesEntity classesEntity){
        if (classesService.update(classesEntity)) {
            return "redirect:/classes/queryAllClasses";
        }else{
            return "redirect:/classes/toUpdateClasses";
        }
    }

    //  删除班级信息
    @SysLog("删除班级信息")
    @RequestMapping(value="doDeleteClasses")
    public String doDeleteClasses(Integer classId){
        classesService.removeById(classId);
        return "redirect:/classes/queryAllClasses";
    }

    //  根据课程ID查询班级信息
//    @ResponseBody
   /* @RequestMapping(value="queryClassByCourseId")
    public String queryClassByCourseId(@RequestParam("cname")String cname, Model model){
        log.info("cname====="+cname);
        List<Integer> courseId = courseService.selectCidByCname(cname);
        List<ClassesEntity> classesList = classesService.queryClassByCourseId(courseId);
        model.addAttribute("classList", classesList);
        return "costDetails/addCostDetails";
    }*/





}
