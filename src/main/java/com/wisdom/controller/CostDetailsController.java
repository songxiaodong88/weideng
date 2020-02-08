package com.wisdom.controller;

import com.wisdom.common.annotation.SysLog;
import com.wisdom.entity.ClassesEntity;
import com.wisdom.entity.CostDetailsEntity;
import com.wisdom.service.ClassesService;
import com.wisdom.service.CostDetailsService;
import com.wisdom.service.CourseService;
import com.wisdom.service.StudentService;
import com.wisdom.utils.HttpContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


/**
 * 学员课程信息总表Controller
 *
 * @author 8888
 * @date 2020-01-19 00:05:04
 */
@Slf4j
@Controller
@RequestMapping("costDetails")
public class CostDetailsController extends AbstractController {

    @Autowired
    private CostDetailsService costDetailsService;
    @Autowired
    private ClassesService classesService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;


    //  去添加学员课程信息页面
    @RequestMapping(value="toAddCostDetails")
    public String toAddCostDeails(String addOrUpd,Model model,@RequestParam(value = "cname",required = false)String cname,@RequestParam(value = "cdId",required = false)Integer cdId,String urlAddress){
        HttpSession session = HttpContextUtils.getHttpServletRequest().getSession();
        logger.info("url==-====="+urlAddress);
        session.setAttribute("url1",urlAddress);
        log.info("cname====="+cname);
        List<Integer> courseId = courseService.selectCidByCname(cname);
        courseId.forEach(i->{
            log.info("courseId========>>"+i);
        });
        List<ClassesEntity> classesList = classesService.queryClassByCourseId(courseService.selectCidByCname(cname));
        classesList.forEach(a->{
            log.info("cname===="+a.getCname()+"   "+a.getSchooltime());
        });
        model.addAttribute("classList", classesList);
        //  判断页面执行操作是添加还是修改
        if (addOrUpd.equals("add")) {
            return "costDetails/addCostDetails";
        }else{
            CostDetailsEntity costDetailsEntity = new CostDetailsEntity();
            costDetailsEntity.setCdId(cdId);
            log.info("cdId===================>>>"+cdId);
            model.addAttribute("costDetails", costDetailsService.detailCostByCdId(cdId));
            log.info("classId-================"+costDetailsService.detailCostByCdId(cdId).getClassId());
            return "costDetails/updateCostDetails";
        }
    }

    //  执行添加操作
    @SysLog("添加学员课程信息")
    @RequestMapping(value="doAddCostDetails")
    public String doAddCostDetails(CostDetailsEntity costDetailsEntity,@RequestParam(value = "payTime1",required = false) String payTime1,@RequestParam("sname")String sname,@RequestParam("periodOfValidity1")String periodOfValidity1){
        HttpSession session = HttpContextUtils.getHttpServletRequest().getSession();
        String url2 = (String) session.getAttribute("url1");
        costDetailsEntity.setCdId(0);
        costDetailsEntity.setStuId(studentService.selectStuIDBySname(sname));
        costDetailsEntity.setCourseId(classesService.queryCourseIdByClassId(costDetailsEntity.getClassId()));
        //  付款日期
        if (null != payTime1 && !"".equals(payTime1)) {
            try {
                costDetailsEntity.setPayTime(new SimpleDateFormat("yyyy-MM-dd").parse(payTime1));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        //  有效期
        if (null != periodOfValidity1 && !"".equals(periodOfValidity1)) {
            try {
                costDetailsEntity.setPeriodOfValidity(new SimpleDateFormat("yyyy-MM-dd").parse(periodOfValidity1));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (costDetailsService.save(costDetailsEntity)) {
            return  "redirect:"+url2;
        }else{
            return "/costDetails/toAddCostDetails";
        }
    }

    //  执行修改学员课程信息操作
    @SysLog("修改学员课程信息")
    @RequestMapping(value="doUpdCostDetails")
    public String doUpdCostDetails(CostDetailsEntity costDetailsEntity,@RequestParam(value = "payTime1",required = false) String payTime1,@RequestParam(value = "sname",required = false)String sname,@RequestParam("periodOfValidity1")String periodOfValidity1){
        HttpSession session = HttpContextUtils.getHttpServletRequest().getSession();
        String url2 = (String) session.getAttribute("url1");
        log.info("url2==========="+url2);
        //  付款日期
        if (null != payTime1 && !"".equals(payTime1)) {
            try {
                costDetailsEntity.setPayTime(new SimpleDateFormat("yyyy-MM-dd").parse(payTime1));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        //  有效期
        if (null != periodOfValidity1 && !"".equals(periodOfValidity1)) {
            try {
                costDetailsEntity.setPeriodOfValidity(new SimpleDateFormat("yyyy-MM-dd").parse(periodOfValidity1));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
//        costDetailsEntity.setStuId(studentService.selectStuIDBySname(sname));
//        costDetailsEntity.setCourseId(classesService.queryCourseIdByClassId(costDetailsEntity.getClassId()));
        if (costDetailsService.update(costDetailsEntity)) {
            return "redirect:"+url2;
        }else{
            return "redirect:/costDetails/toAddCostDetails?addOrUpd=upd&cdId="+Integer.valueOf(costDetailsEntity.getCdId());
        }
    }

    //  执行删除操作
    @SysLog("删除学员课程信息")
    @RequestMapping(value="doDelCostDetails")
    public String doDelCostDetails(Integer cdId,Model model,String urlAddress){
        log.info("cdId======"+cdId);
        if (costDetailsService.delete(cdId)) {
            model.addAttribute("costDel", "删除学员课程信息成功！");
        }else{
            model.addAttribute("costDel", "删除学员课程信息失败！");
        }
        return "redirect:" + urlAddress;
    }



}