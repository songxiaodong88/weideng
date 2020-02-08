package com.wisdom.controller;

import com.alibaba.excel.EasyExcel;
import com.wisdom.common.annotation.SysLog;
import com.wisdom.dto.FolkDanceDTO;
import com.wisdom.excel.entity.FolkDanceExcelEntity;
import com.wisdom.excel.utils.FolkDanceExcelDataListener;
import com.wisdom.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 民族舞
 */
@Controller
@RequestMapping("folkDance")
public class FolkDanceController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassesService classesService;
    @Autowired
    private FolkDanceService folkDanceService;
    @Autowired
    private CostDetailsService costDetailsService;

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(FolkDanceController.class);

    //  查询所有陈老师民族舞信息
    @RequestMapping(value="queryAllFolkDanceChen")
    public String queryAllFolkDanceChen(Model model){
        List<FolkDanceDTO> chenList = folkDanceService.queryAllFolkDance_chen();
        model.addAttribute("chen", chenList);
        model.addAttribute("cJiu", chenList.get(0).getSumJiu());
        model.addAttribute("cShi", chenList.get(0).getSumShi());
        model.addAttribute("cShy", chenList.get(0).getSumShy());
        model.addAttribute("cShe", chenList.get(0).getSumShe());
        model.addAttribute("cTotal", chenList.get(0).getSumTotal());
        return "folkDance/folkDanceList_chen";
    }

    //  查询所有殷老师民族舞信息
    @RequestMapping(value="queryAllFolkDanceYin")
    public String queryAllFolkDanceYin(Model model){
        List<FolkDanceDTO> yinList = folkDanceService.queryAllFolkDance_yin();
        model.addAttribute("yin", yinList);
        model.addAttribute("yJiu", yinList.get(0).getSumJiu());
        model.addAttribute("yShi", yinList.get(0).getSumShi());
        model.addAttribute("yShy", yinList.get(0).getSumShy());
        model.addAttribute("yShe", yinList.get(0).getSumShe());
        model.addAttribute("yTotal", yinList.get(0).getSumTotal());
        return "folkDance/folkDanceList_yin";
    }

    //  查询所有幼小衔接班民族舞信息
    @RequestMapping(value="queryAllFolkDancePRE")
    public String queryAllFolkDancePRE(Model model){
        List<FolkDanceDTO> preList = folkDanceService.queryAllFolkDance_PRE();
        model.addAttribute("pre", preList);
        model.addAttribute("pJiu", preList.get(0).getSumJiu());
        model.addAttribute("pShi", preList.get(0).getSumShi());
        model.addAttribute("pShy", preList.get(0).getSumShy());
        model.addAttribute("pShe", preList.get(0).getSumShe());
        model.addAttribute("pTotal", preList.get(0).getSumTotal());
        return "folkDance/folkDanceList_PRE";
    }


    /**
     * 导入民族舞信息
     * @return
     * @throws Exception
     */
    @SysLog("导入民族舞信息")
    @ResponseBody
    @RequestMapping("/importFolkDanceInfo")
//    @RequiresPermissions("sys:user:importUserInfo")
    public String importFolkDanceInfo(@RequestParam("file") MultipartFile file, Model model,@RequestParam(value = "excelName",required = false) String excelName,@RequestParam(value = "sheetNo",defaultValue = "0") String sheetNo,@RequestParam(value = "headLineNum",defaultValue = "2") String headLineNum) throws Exception{
        if (null == file || file.isEmpty()) {
            model.addAttribute("error", "导入文件不能为空");
            return "导入信息失败";
        }
        FolkDanceExcelDataListener folkDanceExcelDataListener = new FolkDanceExcelDataListener(this.studentService,this.classesService,this.costDetailsService);
        try {
            // headRowNumber(1) 这里可以设置1，因为头就是一行。如果多行头，可以设置其他值。不传入也可以，他没有指定头，也就是默认1行
            EasyExcel.read(file.getInputStream(), FolkDanceExcelEntity.class,folkDanceExcelDataListener).sheet(Integer.valueOf(sheetNo)).headRowNumber(Integer.valueOf(headLineNum)).doRead();
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
        Map<String, Object> data = folkDanceExcelDataListener.getData();
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
