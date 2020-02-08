package com.wisdom.excel.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * 幼小班
 *
 * @author 8888
 * @date 2020-01-23 23:33:48
 */
@Data
public class NurseryClassExcelEntity extends BaseRowModel {

    //  学生姓名
    @ExcelProperty(value = "学生姓名",index = 0)
    private String sname;
    //  缴费金额
    @ExcelProperty(value = "缴费金额",index = 1)
    private String total;
    //  课时/月
    @ExcelProperty(value = "课时/月",index = 2)
    private String classHour;
    //  截止时间
    @ExcelProperty(value = "截止时间",index = 3)
    private String periodOfValidity;
    //  缴费时间
    @ExcelProperty(value = "缴费时间",index = 4)
    private String payTime;
    //  联系方式
    @ExcelProperty(value = "联系方式",index = 5)
    private String phone;
    //  单价/月
    @ExcelProperty(value = "单价/月",index = 6)
    private String price;
    //  八月
    @ExcelProperty(value = "八月",index = 7)
    private String baYue;
    //  九月
    @ExcelProperty(value = "九月",index = 8)
    private String jiuYue;
    //  十月
    @ExcelProperty(value = "十月",index = 9)
    private String shiYue;
    //  十一月
    @ExcelProperty(value = "十一月",index = 10)
    private String shiYiYue;
    //  十二月
    @ExcelProperty(value = "十二月",index = 11)
    private String shiErYue;
    //  一月
    @ExcelProperty(value = "一月",index = 12)
    private String yiYue;
    //  二月
    @ExcelProperty(value = "二月",index = 13)
    private String erYue;
    //  三月
    @ExcelProperty(value = "三月",index = 14)
    private String sanYue;
    //  四月
    @ExcelProperty(value = "四月",index = 15)
    private String siYue;
    //  五月
    @ExcelProperty(value = "五月",index = 16)
    private String wuYue;
    //  六月
    @ExcelProperty(value = "六月",index = 17)
    private String liuYue;
    //  七月
//    @ExcelProperty(value = "七月",index = 18)
//    private Double july;
    //  课外班
    @ExcelProperty(value = "课外班",index = 18)
    private String interestClasses;
    //  备注
    @ExcelProperty(value = "备注",index = 19)
    private String remark;
    //  学平险
    @ExcelProperty(value = "学平险",index = 20)
    private String studentsSafetyInsurance;
    //  档案
    @ExcelProperty(value = "档案",index = 21)
    private String record;
}
