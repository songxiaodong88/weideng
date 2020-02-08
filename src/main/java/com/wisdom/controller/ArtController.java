package com.wisdom.controller;

import com.alibaba.excel.EasyExcel;
import com.wisdom.common.annotation.SysLog;
import com.wisdom.dto.ArtDTO;
import com.wisdom.excel.entity.ArtExcelEntity;
import com.wisdom.excel.utils.ArtExcelDataListener;
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
 * 美术
 */
@Controller
@RequestMapping("art")
public class ArtController {

    @Autowired
    private ArtService artService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassesService classesService;
    @Autowired
    private CostDetailsService costDetailsService;

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(ArtController.class);

    //  查询所有绘画部信息
    @RequestMapping(value="queryAllArt")
    public String queryAllArt(Model model){
        List<ArtDTO> artList = artService.queryAllArt();
        model.addAttribute("artList", artList);
        model.addAttribute("artJiu", artList.get(0).getSumJiu());
        model.addAttribute("artShi", artList.get(0).getSumShi());
        model.addAttribute("artShy", artList.get(0).getSumShy());
        model.addAttribute("artShe", artList.get(0).getSumShe());
        model.addAttribute("artTotal", artList.get(0).getSumTotal());
        return "art/artList";
    }








    /**
     * 导入美术信息
     * @return
     * @throws Exception
     */
    @SysLog("导入美术信息")
    @ResponseBody
    @RequestMapping("/importArtInfo")
//    @RequiresPermissions("sys:user:importUserInfo")
    public String importArtInfo(@RequestParam("file") MultipartFile file, Model model,@RequestParam(value = "excelName",required = false) String excelName,@RequestParam(value = "sheetNo",defaultValue = "0") String sheetNo,@RequestParam(value = "headLineNum",defaultValue = "2") String headLineNum) throws Exception{
        if (null == file || file.isEmpty()) {
            model.addAttribute("error", "导入文件不能为空");
            return "导入信息失败";
        }
        ArtExcelDataListener artExcelDataListener = new ArtExcelDataListener(this.studentService,this.classesService,this.costDetailsService);
        try {
            // headRowNumber(1) 这里可以设置1，因为头就是一行。如果多行头，可以设置其他值。不传入也可以，他没有指定头，也就是默认1行
            EasyExcel.read(file.getInputStream(), ArtExcelEntity.class,artExcelDataListener).sheet(Integer.valueOf(sheetNo)).headRowNumber(Integer.valueOf(headLineNum)).doRead();
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
        Map<String, Object> data = artExcelDataListener.getData();
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
