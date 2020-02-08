package com.wisdom.excel.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class OilPaintingExcelEntity {

    //  姓名
    @ExcelProperty(value = "姓名",index = 0)
    private String sname;
    //  科目
    @ExcelProperty(value = "科目",index = 1)
    private String course;
    //  报课时间
    @ExcelProperty(value = "报课时间",index = 2)
    private String registrationTime;
    //  缴费金额
    @ExcelProperty(value = "缴费金额",index = 3)
    private String total;
    //  报课次数(含赠课)
    @ExcelProperty(value = "报课次数(含赠课)",index = 4)
    private String classHour;
    //  赠课次数
    @ExcelProperty(value = "赠课次数",index = 5)
    private String giveLessons;
    //  每次课费用
    @ExcelProperty(value = "每次课费用",index = 6)
    private String price;
    //  18年11月
    @ExcelProperty(value = "18年11月",index = 7)
    private String yibaNovember;
    //  18年12月
    @ExcelProperty(value = "18年12月",index = 8)
    private String yibaDecember;
    //  19年1月
    @ExcelProperty(value = "19年1月",index = 9)
    private String yijiuJanuary;
    //  19年3月
    @ExcelProperty(value = "19年3月",index = 10)
    private String yijiuMarch;
    //  19年4月
    @ExcelProperty(value = "19年4月",index = 11)
    private String yijuApril;
    //  19年5月
    @ExcelProperty(value = "19年5月",index = 12)
    private String yijiuMay;
    //  19年6月
    @ExcelProperty(value = "19年6月",index = 13)
    private String yijiuJune;
    //  19年7月
    @ExcelProperty(value = "19年7月",index = 14)
    private String yijiuJuly;
    //  19年9月
    @ExcelProperty(value = "19年9月",index = 15)
    private String yijiuSeptember;
    //  19年10月
    @ExcelProperty(value = "19年10月",index = 16)
    private String yijiuOctober;
    //  19年11月
    @ExcelProperty(value = "19年11月",index = 17)
    private String yijiuNovember;
    //  19年12月
    @ExcelProperty(value = "19年12月",index = 18)
    private String yijiuDecember;

}
