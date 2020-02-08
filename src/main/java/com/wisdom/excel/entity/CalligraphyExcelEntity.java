package com.wisdom.excel.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * 书法
 *
 * @author 8888
 * @date 2020-01-17 23:19:29
 */
@Data
public class CalligraphyExcelEntity extends BaseRowModel {

    @ExcelProperty(value="班级名称",index=0)
    //  班级名称
    private String cname;
    //   上课时间
    @ExcelProperty(value="上课时间",index=1)
    private String schooltime;
    //   姓名
    @ExcelProperty(value = "姓名", index = 2)
    private String sname;
    //  总课次数
    @ExcelProperty(value = "总课次数", index = 3)
    private String classHour;
    //  课次单价
    @ExcelProperty(value = "课次单价", index = 4)
    private String cost;
    //  学费
    @ExcelProperty(value = "学费", index = 5)
    private String total;
    //  缴费时间
    @ExcelProperty(value = "缴费日期", index = 6)
    private String payTime;
    //  9月消课
    @ExcelProperty(value = "9月消课", index = 7)
    private String jiuYue;
    //  10月消课
    @ExcelProperty(value = "10月消课", index = 8)
    private String shiYue;
    //  11月消课
    @ExcelProperty(value = "11月消课", index = 9)
    private String shiYiYue;
    //  12月消课
    @ExcelProperty(value = "12月消课", index = 10)
    private String shiErYue;

}
