package com.wisdom.controller;

import com.wisdom.excel.entity.AoShuExcelEntity;
import com.wisdom.excel.entity.EnglishExcelEntity;
import com.wisdom.excel.utils.EasyExcelUtil;
import com.wisdom.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.util.List;

/**
 * 书法
 */
@RestController
@RequestMapping("easyExcel")
public class EasyExcelController {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

        @RequestMapping(value="importExcel")
        public String importExcel(@RequestParam("file") MultipartFile file, Model model,@RequestParam(value = "excelName",required = false) String excelName,@RequestParam(value = "sheetNo",/*defaultValue = "0",*/required = false) String sheetNo,@RequestParam(value = "headLineNum",defaultValue = "2") String headLineNum) throws Exception{

//                InputStream in = new FileInputStream("E://aaa.xlsx");
            //读取的工具类可以根据自己需要封装
            //我这边是需要List集合所以封装了集合

            if (excelName.equalsIgnoreCase("english")) {
                List<EnglishExcelEntity> excelContentList = EasyExcelUtil.getExcelContent(new BufferedInputStream(file.getInputStream()), EnglishExcelEntity.class,Integer.valueOf(sheetNo),Integer.valueOf(headLineNum));
            }
            if (excelName.equalsIgnoreCase("aoShu")) {
                List<AoShuExcelEntity> excelContentList = EasyExcelUtil.getExcelContent(new BufferedInputStream(file.getInputStream()), AoShuExcelEntity.class,Integer.valueOf(sheetNo),Integer.valueOf(headLineNum));
            }
          /*  excelContentList.forEach(e -> {
                System.out.println(e.toString());
            });*/
            return "导入信息成功！";
        }

    }





