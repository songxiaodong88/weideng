package com.wisdom.controller;

import com.alibaba.excel.EasyExcel;
import com.wisdom.common.annotation.SysLog;
import com.wisdom.entity.CostDetailsEntity;
import com.wisdom.entity.StudentEntity;
import com.wisdom.entity.UsersEntity;
import com.wisdom.excel.entity.CourseExcelEntity;
import com.wisdom.excel.entity.StudentExcelEntity;
import com.wisdom.excel.utils.CourseExcelDataListener;
import com.wisdom.excel.utils.StudentExcelDataListener;
import com.wisdom.service.CostDetailsService;
import com.wisdom.service.CourseService;
import com.wisdom.service.NurseryClassService;
import com.wisdom.service.StudentService;
import com.wisdom.utils.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * 学生
 */
@Controller
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private CostDetailsService costDetailsService;
    @Autowired
    private NurseryClassService nurseryClassService;
    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    //  查询所有学生信息
    @RequestMapping(value="queryAllStudent")
    public String queryAllStudent(Model model){
        List<StudentEntity> stuList = studentService.queryAllStudent();
        model.addAttribute("stuList", stuList);
        return "infoManagement/studentList";
    }

    //  查询所有退学学生信息
    @RequestMapping(value="queryAllStudentByTuiXue")
    public String queryAllStudentByTuiXue(Model model){
        List<StudentEntity> stuList = studentService.queryAllStudent();
        model.addAttribute("stuTx", stuList);
        return "infoManagement/studentList";
    }

    //  去添加学生信息页面
    @GetMapping(value="toAddStudent")
    public String toAddStudent(){
        return "student/addStudent";
    }

    //  去修改学生信息页面
    @GetMapping(value="toUpdStudent")
    public String toUpdStudent(Integer sid,Model model){
        model.addAttribute("stu", studentService.detailStudentBySid(sid));
        return "student/updateStudent";
    }

    //  执行修改学生信息操作
    @SysLog("修改学生信息")
    @RequestMapping(value="doUpdStudent")
    public String doUpdStudent(StudentEntity studentEntity,@RequestParam("birthday1")String birthday1){
        logger.info("updSid========="+studentEntity.getSid());
        try {
            studentEntity.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday1));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (studentService.update(studentEntity)) {
            return "redirect:/student/queryAllStudent";
        }
        return "student/updateStudent";
    }

    //    查看学生姓名是否存在
    @ResponseBody
    @RequestMapping(value="existsStuName")
    public boolean existsStuName(String sname){
        logger.info("sname============="+sname);
        if (null != studentService.selectStuIDBySname(sname) && studentService.selectStuIDBySname(sname) > 0) {
            return false;
        }else{
            return true;
        }
    }

    //    增加学员课程信息时查看学生姓名是否存在
    @ResponseBody
    @RequestMapping(value="isExistsStuName")
    public boolean isExistsStuName(String sname){
        logger.info("stuName============="+sname);
        if (null != studentService.selectStuIDBySname(sname) && studentService.selectStuIDBySname(sname) > 0) {
            return false;
        }else{
            return true;
        }
    }

    //  执行添加操作
    @SysLog("添加学生信息")
    @RequestMapping(value="doAddStudent")
    public String doAddStudent(StudentEntity studentEntity,@RequestParam("birthday1")String birthday1){
        try {
            studentEntity.setSid(0);
            studentEntity.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday1));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (studentService.save(studentEntity)) {
            return "redirect:/student/queryAllStudent";
        }else{
            return "student/addStudent";
        }
    }

    //  删除学生信息
    @SysLog("删除学生信息")
    @ResponseBody
    @RequestMapping(value="doDelStudent")
    public boolean doDelStudent(Integer sid){
        //  根据学生ID查询该学生是否有未解除的课程
        List<CostDetailsEntity> list = costDetailsService.queryCostDetailsByStuId(sid);
        boolean flag = true;
        if (list != null && !list.isEmpty()) {
            flag=false;
        }
        if (null != nurseryClassService.queryNurseyClassByStuId(sid)) {
            flag = false;
        }
        if (flag) {
            //  根据学生ID查询该学生没有课程
            studentService.updStudentOfStatus(sid);
        }
        return flag;
    }

    /**
     * 导入学生信息
     * @return
     * @throws Exception
     */
    @SysLog("导入学生信息")
    @ResponseBody
    @RequestMapping("/importStudentInfo")
//    @RequiresPermissions("sys:user:importUserInfo")
    public String importStudentInfo(@RequestParam("file") MultipartFile file, Model model) throws Exception{
        if (null == file || file.isEmpty()) {
            model.addAttribute("error", "导入文件不能为空");
            return "导入学生信息失败";
        }
        StudentExcelDataListener studentExcelDataListener = new StudentExcelDataListener(this.studentService);
        try {
            // headRowNumber(1) 这里可以设置1，因为头就是一行。如果多行头，可以设置其他值。不传入也可以，他没有指定头，也就是默认1行
            EasyExcel.read(file.getInputStream(), StudentExcelEntity.class,studentExcelDataListener).sheet(2).headRowNumber(2).doRead();
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
        Map<String, Object> data = studentExcelDataListener.getData();
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

}
