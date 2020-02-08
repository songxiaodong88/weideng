package com.wisdom.controller;

import com.alibaba.excel.EasyExcel;
import com.wisdom.common.annotation.SysLog;
import com.wisdom.dto.WuShuDTO;
import com.wisdom.excel.entity.WuShuExcelEntity;
import com.wisdom.excel.utils.WuShuExcelDataListener;
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
 * 武术
 */
@Controller
@RequestMapping("wuShu")
public class WuShuController {

    @Autowired
    private WuShuService wuShuService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassesService classesService;
    @Autowired
    private CostDetailsService costDetailsService;

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(WuShuController.class);

    //  查询所有武术部信息
    @RequestMapping(value="queryAllWuShu")
    public String queryAllWuShu(Model model){
        List<WuShuDTO> wuShuList = wuShuService.queryAllWuShu();
        model.addAttribute("wuSum", wuShuList.get(0).getSumTotal());
        model.addAttribute("wuShu", wuShuList);
        return "wuShu/wuShuList";
    }




    /**
     * 导入武术信息
     * @return
     * @throws Exception
     */
    @SysLog("导入武术信息")
    @ResponseBody
    @RequestMapping("/importWuShuInfo")
//    @RequiresPermissions("sys:user:importUserInfo")
    public String importWuShuInfo(@RequestParam("file") MultipartFile file, Model model,@RequestParam("sheetNo")String sheetNo,@RequestParam(value = "headLineNum",defaultValue = "2")String headLineNum) throws Exception{
        if (null == file || file.isEmpty()) {
            model.addAttribute("error", "导入文件不能为空");
            return "导入信息失败";
        }
        WuShuExcelDataListener wuShuExcelDataListener = new WuShuExcelDataListener(this.studentService,this.classesService,this.costDetailsService);
        try {
            // headRowNumber(1) 这里可以设置1，因为头就是一行。如果多行头，可以设置其他值。不传入也可以，他没有指定头，也就是默认1行
            EasyExcel.read(file.getInputStream(), WuShuExcelEntity.class,wuShuExcelDataListener).sheet(Integer.valueOf(sheetNo)).headRowNumber(Integer.valueOf(headLineNum)).doRead();
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
        Map<String, Object> data = wuShuExcelDataListener.getData();
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
