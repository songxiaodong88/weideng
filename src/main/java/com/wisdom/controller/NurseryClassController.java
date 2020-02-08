package com.wisdom.controller;

import com.alibaba.excel.EasyExcel;
import com.wisdom.common.annotation.SysLog;
import com.wisdom.dto.NurseryClassDTO;
import com.wisdom.entity.NurseryClassEntity;
import com.wisdom.excel.entity.NurseryClassExcelEntity;
import com.wisdom.excel.entity.PRESchoolExcelEntity;
import com.wisdom.excel.utils.NurseryClassExcelDataListener;
import com.wisdom.excel.utils.PRESchoolExcelDataListener;
import com.wisdom.service.*;
import com.wisdom.utils.HttpContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * 幼儿班/幼小衔接班
 */
@Controller
@RequestMapping("nurseryClass")
public class NurseryClassController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private NurseryClassService nurseryClassService;

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(NurseryClassController.class);

    //  查询所有幼儿班信息
    @RequestMapping(value="queryAllNurseryClass")
    public String queryAllNurseryClass(Model model){
        List<NurseryClassDTO> ncList = nurseryClassService.queryAllNurseryClass();
        model.addAttribute("nursery", ncList);
        model.addAttribute("ncBa", ncList.get(0).getSumBa());
        model.addAttribute("ncJiu", ncList.get(0).getSumJiu());
        model.addAttribute("ncShi", ncList.get(0).getSumShi());
        model.addAttribute("ncShy", ncList.get(0).getSumShy());
        model.addAttribute("ncShe", ncList.get(0).getSumShe());
        model.addAttribute("ncYi", ncList.get(0).getSumYi());
        model.addAttribute("ncTotal", ncList.get(0).getSumTotal());
        return "nurseryClass/nurseryClassList";
    }

    //  去添加幼儿班页面
    @GetMapping(value="toAddNurseryClass")
    public String toAddNurseryClass(String urlAddress,Integer classId){
        HttpSession session = HttpContextUtils.getHttpServletRequest().getSession();
        session.setAttribute("ncUrl",urlAddress);
        session.setAttribute("clazzId",classId);
        return "nurseryClass/addNurseryClass";
    }

    //  执行添加操作
    @SysLog("添加幼儿班/幼小衔接班课程信息")
    @RequestMapping(value="doAddNurseryClass")
    public String doAddNurseryClass(NurseryClassEntity nurseryClassEntity,String payTime1,@RequestParam(value = "startTime",required = false) String startTime,@RequestParam(value = "endTime",required = false)String endTime,
                                    @RequestParam(value = "studentsSafetyInsurance",required = false)Integer studentsSafetyInsurance,@RequestParam(value = "record",required = false)Integer record,
                                    @RequestParam(value = "protocol",required = false)Integer protocol,String sname){
        logger.info("payTime======"+nurseryClassEntity.getPayTime());
        HttpSession session = HttpContextUtils.getHttpServletRequest().getSession();
        //  付款日期
       /* if (null != payTime1 && !"".equals(payTime1)) {
            try {
                nurseryClassEntity.setPayTime(new SimpleDateFormat("yyyy-MM-dd").parse(payTime1));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }*/
        Integer classId = Integer.valueOf(String.valueOf(session.getAttribute("clazzId")));
        Integer clazzId = 0;//  班级编号
        Integer courseId = 0;// 课程编号
        String url2 = (String)session.getAttribute("ncUrl");
        String yxq = "";
        StringBuilder sb = new StringBuilder();
        if (startTime != null && !"".equals(startTime)) {
            sb.append(startTime);
            sb.append("-");
            sb.append(endTime);
        }
        yxq = sb.toString();
        if (classId == 32) {
            clazzId = 32;
            courseId = 2;
        }else{
            clazzId = 33;
            courseId = 1;
        }
        //  学平险
        if (1 == studentsSafetyInsurance) {
            nurseryClassEntity.setStudentsSafetyInsurance("√");
        }else {
            nurseryClassEntity.setStudentsSafetyInsurance("");
        }
        //  档案
        if (1 == protocol) {
            nurseryClassEntity.setProtocol("√");
        }else{
            nurseryClassEntity.setProtocol("");
        }
        //  协议
        if (1 == record) {
            nurseryClassEntity.setRecord("√");
        }else{
            nurseryClassEntity.setRecord("");
        }
        nurseryClassEntity.setNcId(0);
        nurseryClassEntity.setClassId(clazzId);
        nurseryClassEntity.setCourseId(courseId);
        nurseryClassEntity.setPeriodOfValidity(yxq);
        nurseryClassEntity.setStuId(studentService.selectStuIDBySname(sname));
        if (nurseryClassService.save(nurseryClassEntity)) {
            return "redirect:" + url2;
        }else{
            return "nurseryClass/toAddNurseryClass";
        }
    }

    //  去修改页面
    @RequestMapping(value = "toUpdateNurseryClass")
    public String toUpdateNurseryClass(Integer ncId,Integer courseId,Model model,String urlAddress) {
        NurseryClassDTO nurseryClassDTO = nurseryClassService.detailNurseryClassByNcId(courseId, ncId);
        HttpSession session = HttpContextUtils.getHttpServletRequest().getSession();
        logger.info("stu===="+nurseryClassDTO.getStudentsSafetyInsurance()+"   reco=====>"+nurseryClassDTO.getRecord()+"    pro=====>"+nurseryClassDTO.getProtocol());
        //  学平险
        if (nurseryClassDTO.getStudentsSafetyInsurance() != null && !"".equals(nurseryClassDTO.getStudentsSafetyInsurance())) {
            model.addAttribute("studentsSafetyInsurance", "1");
        } else {
            model.addAttribute("studentsSafetyInsurance", "2");
        }
        //  档案
        if (nurseryClassDTO.getRecord() != null && !"".equals(nurseryClassDTO.getRecord())) {
            model.addAttribute("record", "1");
        }else{
            model.addAttribute("record", "2");
        }
        //  协议
        if (nurseryClassDTO.getProtocol() != null && !"".equals(nurseryClassDTO.getProtocol())) {
            model.addAttribute("protocol", "1");
        } else {
            model.addAttribute("protocol", "2");
        }
        model.addAttribute("courseId", courseId);
        model.addAttribute("nurseryClass", nurseryClassDTO);
        session.setAttribute("updUrl",urlAddress);
        return "nurseryClass/updateNurseryClass";
    }

    //  执行修改操作
    @SysLog("修改幼儿班/幼小衔接班课程信息")
    @RequestMapping(value="doUpdateNurseryClass")
    public String doUpdateNurseryClass(NurseryClassEntity nurseryClassEntity,@RequestParam(value = "startTime",required = false) String startTime,@RequestParam(value = "endTime",required = false)String endTime,
                                       @RequestParam(value = "studentsSafetyInsurance",required = false)Integer studentsSafetyInsurance,@RequestParam(value = "record",required = false)Integer record,
                                       @RequestParam(value = "protocol",required = false)Integer protocol){
        logger.info("payTime======"+nurseryClassEntity.getPayTime());
        HttpSession session = HttpContextUtils.getHttpServletRequest().getSession();
        //  付款日期
       /* if (null != payTime1 && !"".equals(payTime1)) {
            try {
                nurseryClassEntity.setPayTime(new SimpleDateFormat("yyyy-MM-dd").parse(payTime1));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }*/
        String url2 = (String)session.getAttribute("updUrl");
        String yxq = "";
        StringBuilder sb = new StringBuilder();
        if (startTime != null && !"".equals(startTime)) {
            sb.append(startTime);
            sb.append("-");
            sb.append(endTime);
            yxq = sb.toString();
            nurseryClassEntity.setPeriodOfValidity(yxq);
        }
        //  学平险
        if (1 == studentsSafetyInsurance) {
            nurseryClassEntity.setStudentsSafetyInsurance("√");
        }else {
            nurseryClassEntity.setStudentsSafetyInsurance("");
        }
        //  档案
        if (1 == protocol) {
            nurseryClassEntity.setProtocol("√");
        }else{
            nurseryClassEntity.setProtocol("");
        }
        //  协议
        if (1 == record) {
            nurseryClassEntity.setRecord("√");
        }else{
            nurseryClassEntity.setRecord("");
        }
//        if (nurseryClassService.update(nurseryClassEntity)) {
        if (nurseryClassService.updateNurseryClass(nurseryClassEntity) > 0) {
            return "redirect:" + url2;
        } else {
            return "redirect:/nurseryClass/toUpdateNurseryClass?ncId=" + nurseryClassEntity.getNcId() + "&courseId=" + nurseryClassEntity.getCourseId();
        }
    }

    // 删除课程信息
    @SysLog("删除幼儿班/幼小衔接班课程信息")
    @RequestMapping(value="doDeleteNurseryClass")
    public String doDeleteNurseryClass(Integer ncId){
        HttpSession session = HttpContextUtils.getHttpServletRequest().getSession();
        String url2 = (String)session.getAttribute("ncUrl");
        nurseryClassService.updNurseryClassStatusByNcId(ncId);
        return "redirect:/nurseryClass/queryAllNurseryClass";
    }


    /**
     * 导入幼小班信息
     * @return
     * @throws Exception
     */
    @SysLog("导入幼小班信息")
    @ResponseBody
    @RequestMapping("/importNurseryClassInfo")
//    @RequiresPermissions("sys:user:importUserInfo")
    public String importNurseryClassInfo(@RequestParam("file") MultipartFile file, Model model,@RequestParam(value = "excelName",required = false) String excelName,@RequestParam(value = "sheetNo",defaultValue = "0") String sheetNo,@RequestParam(value = "headLineNum",defaultValue = "2") String headLineNum) throws Exception{
        if (null == file || file.isEmpty()) {
            model.addAttribute("error", "导入文件不能为空");
            return "导入信息失败";
        }
        NurseryClassExcelDataListener nurseryClassExcelDataListener = new NurseryClassExcelDataListener(this.studentService,this.nurseryClassService);
        try {
            // headRowNumber(1) 这里可以设置1，因为头就是一行。如果多行头，可以设置其他值。不传入也可以，他没有指定头，也就是默认1行
            EasyExcel.read(file.getInputStream(), NurseryClassExcelEntity.class,nurseryClassExcelDataListener).sheet(Integer.valueOf(sheetNo)).headRowNumber(Integer.valueOf(headLineNum)).doRead();
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
        Map<String, Object> data = nurseryClassExcelDataListener.getData();
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

    /**
     * 导入幼小衔接班信息
     * @return
     * @throws Exception
     */
    @SysLog("导入幼小衔接班信息")
    @RequestMapping("/importPRESchoolInfo")
//    @RequiresPermissions("sys:user:importUserInfo")
    public String importPRESchoolInfo(@RequestParam("file") MultipartFile file, Model model,@RequestParam(value = "excelName",required = false) String excelName,@RequestParam(value = "sheetNo",defaultValue = "0") String sheetNo,@RequestParam(value = "headLineNum",defaultValue = "2") String headLineNum) throws Exception{
        if (null == file || file.isEmpty()) {
            model.addAttribute("error", "导入文件不能为空");
            return "导入信息失败";
        }
        PRESchoolExcelDataListener preSchoolExcelDataListener = new PRESchoolExcelDataListener(this.studentService,this.nurseryClassService);
        try {
            // headRowNumber(1) 这里可以设置1，因为头就是一行。如果多行头，可以设置其他值。不传入也可以，他没有指定头，也就是默认1行
            EasyExcel.read(file.getInputStream(), PRESchoolExcelEntity.class,preSchoolExcelDataListener).sheet(Integer.valueOf(sheetNo)).headRowNumber(Integer.valueOf(headLineNum)).doRead();
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
        Map<String, Object> data = preSchoolExcelDataListener.getData();
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

    @SysLog("测试打印日志")
    @RequestMapping(value="test888")
    public String test888(){
        return "***********";
    }
}
