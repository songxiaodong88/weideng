package com.wisdom.controller;

import com.alibaba.excel.EasyExcel;
import com.wisdom.entity.CourseEntity;
import com.wisdom.service.CourseService;
import com.wisdom.common.annotation.SysLog;
import com.wisdom.excel.entity.CourseExcelEntity;
import com.wisdom.excel.utils.CourseExcelDataListener;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("course")
public class CourseController {

    @Autowired
    private CourseService courseService;
    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    //  查询所有课程信息
    @RequestMapping(value="queryAllCourse")
    public String queryAllCourse(Model model){
        List<CourseEntity> cList = courseService.queryAllCourse();
        model.addAttribute("courseList", cList);
        return "infoManagement/coursesList";
    }

    //  根据课程名称查询课程ID
    @ResponseBody
    @RequestMapping(value="selectCidByCname")
    public List<Integer> selectCidByCname(@RequestParam("cname") String cname){
        return courseService.selectCidByCname(cname);
    }

    //  去添加课程信息页面
    @GetMapping(value="toAddCourse")
    public String toAddCourse(){
        return "course/addCourse";
    }

    //  执行添加课程信息操作
    @SysLog("添加课程信息")
    @RequestMapping(value="doAddCourse")
    public String doAddCourse(CourseEntity courseEntity){
        courseEntity.setCid(0);
        if (courseService.save(courseEntity)) {
            return "redirect:/course/queryAllCourse";
        }else{
            return "redirect:/course/toAddCourse";
        }
    }

    //  去修改课程信息页面
    @RequestMapping(value="toUpdateCourse")
    public String toUpdateCourse(Integer cid,Model model){
        model.addAttribute("courseInfo", courseService.detailCourseByCid(cid));
        return "course/updateCourse";
    }

    //  执行修改课程信息操作
    @SysLog("修改课程信息")
    @RequestMapping(value="doUpdateCourse")
    public String doUpdateCourse(CourseEntity courseEntity){
        if (courseService.update(courseEntity)) {
            return "redirect:/course/queryAllCourse";
        }else{
            return "redirect:/course/toUpdateCourse";
        }
    }

    //  删除课程信息
    @SysLog("删除课程信息")
    @RequestMapping(value="doDeleteCourse")
    public String doDeleteCourse(Integer cid){
        courseService.removeById(cid);
        return "redirect:/course/queryAllCourse";
    }


    /**
     * 导入收费标准
     * @return
     * @throws Exception
     */
    @SysLog("导入收费标准信息")
    @ResponseBody
    @RequestMapping("/importCourseInfo")
//    @RequiresPermissions("sys:user:importUserInfo")
    public String importCourseInfo(@RequestParam("file") MultipartFile file, Model model) throws Exception{
        if (null == file || file.isEmpty()) {
            model.addAttribute("error", "导入收费标准文件不能为空");
            return "导入收费标准失败";
        }
        CourseExcelDataListener courseExcelDataListener = new CourseExcelDataListener(this.courseService);
        try {
            // headRowNumber(1) 这里可以设置1，因为头就是一行。如果多行头，可以设置其他值。不传入也可以，他没有指定头，也就是默认1行
            EasyExcel.read(file.getInputStream(), CourseExcelEntity.class,courseExcelDataListener).sheet().headRowNumber(2).doRead();
//            EasyExcel.read(importUserInfo.getFile().getInputStream(), SysUserData.class,userExcelDataListener).sheet().headRowNumber(1).doRead();

        }catch (IOException ioe){
            logger.info("读取excel异常={}",ioe);
        }
//        //保存文件信息
//        SysOssEntity ossEntity = new SysOssEntity();
//        ossEntity.setUrl(url);
//        ossEntity.setCreateDate(new Date());
//        sysOssService.save(ossEntity);

//        RestResponse restResponse = this.dealerPlanService.processImportData(objects);
        Map<String, Object> data = courseExcelDataListener.getData();
        String exception = data.get("exception") + "";
        String success = data.get("success")+"";
        int exceptionCount = Integer.parseInt(exception);
        int su = Integer.parseInt(success);
        if (exceptionCount > 0) {
            return "导入信息失败！";
        }else{
            return "导入信息成功！";
        }
    }

    @RequestMapping(value="test88")
    public String test88(){
        return "888888888888888888";
    }
}
