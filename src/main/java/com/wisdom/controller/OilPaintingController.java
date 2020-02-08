package com.wisdom.controller;

import com.alibaba.excel.EasyExcel;
import com.wisdom.common.annotation.SysLog;
import com.wisdom.excel.entity.OilPaintingExcelEntity;
import com.wisdom.excel.utils.OilPaintingExcelDataListener;
import com.wisdom.service.OilPaintingService;
import com.wisdom.service.StudentService;
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
import java.util.Map;

/**
 * 油画、素描
 */
@Controller
@RequestMapping("oilPainting")
public class OilPaintingController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private OilPaintingService oilPaintingService;

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    //  查询所有油画信息
    @RequestMapping(value="queryAllOilPainting")
    public String queryAllOilPainting(){
        return "oilPainting/oilPaintingList";
    }



    /**
     * 导入油画、素描信息
     * @return
     * @throws Exception
     */
    @SysLog("导入油画、素描信息")
    @ResponseBody
    @RequestMapping("/importOilPaintingInfo")
//    @RequiresPermissions("sys:user:importUserInfo")
    public String importNurseryClassInfo(@RequestParam("file") MultipartFile file, Model model,@RequestParam(value = "excelName",required = false) String excelName,@RequestParam(value = "sheetNo",defaultValue = "0") String sheetNo,@RequestParam(value = "headLineNum",defaultValue = "2") String headLineNum) throws Exception{
        if (null == file || file.isEmpty()) {
            model.addAttribute("error", "导入文件不能为空");
            return "导入信息失败";
        }
        OilPaintingExcelDataListener oilPaintingExcelDataListener = new OilPaintingExcelDataListener(this.studentService,this.oilPaintingService);
        try {
            // headRowNumber(1) 这里可以设置1，因为头就是一行。如果多行头，可以设置其他值。不传入也可以，他没有指定头，也就是默认1行
            EasyExcel.read(file.getInputStream(), OilPaintingExcelEntity.class,oilPaintingExcelDataListener).sheet(Integer.valueOf(sheetNo)).headRowNumber(Integer.valueOf(headLineNum)).doRead();
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
        Map<String, Object> data = oilPaintingExcelDataListener.getData();
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
