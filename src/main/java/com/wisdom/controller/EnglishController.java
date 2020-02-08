package com.wisdom.controller;

import com.alibaba.excel.EasyExcel;
import com.wisdom.common.annotation.SysLog;
import com.wisdom.dto.EnglishDTO;
import com.wisdom.excel.entity.EnglishExcelEntity;
import com.wisdom.excel.utils.EnglishExcelDataListener;
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

@Controller
@RequestMapping("english")
public class EnglishController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassesService classesService;
    @Autowired
    private EnglishService englishService;
    @Autowired
    private CostDetailsService costDetailsService;

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

      // 查询英语部信息
    @RequestMapping(value="queryAllEnglish")
    public String queryAllEnglish(Model model){
        List<EnglishDTO> list = englishService.queryAllEnglish();
        model.addAttribute("english", list);
        model.addAttribute("jiu", list.get(0).getSumJiu());
        model.addAttribute("shi", list.get(0).getSumShi());
        model.addAttribute("shiYi", list.get(0).getSumShy());
        model.addAttribute("shiEr", list.get(0).getSumShe());
        model.addAttribute("sum",list.get(0).getSumTotal());
        return "english/englishList";

    }





    /**
     * 导入英语信息
     * @return
     * @throws Exception
     */
    @SysLog("导入英语信息")
    @ResponseBody
    @RequestMapping("/importEnglishInfo")
//    @RequiresPermissions("sys:user:importUserInfo")
    public String importEnglishInfo(@RequestParam("file") MultipartFile file, Model model,@RequestParam("sheetNo")String sheetNo,@RequestParam(value = "headLineNum",defaultValue = "2")String headLineNum) throws Exception{
        if (null == file || file.isEmpty()) {
            model.addAttribute("error", "导入英语文件不能为空");
            return "导入英语信息失败";
        }
        EnglishExcelDataListener englishExcelDataListener = new EnglishExcelDataListener(this.studentService,this.classesService,this.costDetailsService);
        try {
            // headRowNumber(1) 这里可以设置1，因为头就是一行。如果多行头，可以设置其他值。不传入也可以，他没有指定头，也就是默认1行
            EasyExcel.read(file.getInputStream(), EnglishExcelEntity.class,englishExcelDataListener).sheet(Integer.valueOf(sheetNo)).headRowNumber(Integer.valueOf(headLineNum)).doRead();
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
        Map<String, Object> data = englishExcelDataListener.getData();
        String exception = data.get("exception") + "";
        String success = data.get("success")+"";
        int exceptionCount = Integer.parseInt(exception);
        int su = Integer.parseInt(success);
        if (exceptionCount > 0) {
            return "导入英语信息失败！";
        }else{
            return "导入英语信息成功！";
        }
    }
}
