package com.wisdom.excel.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * 学生信息表
 *
 * @author 8888
 * @date 2020-01-17 23:30:30
 */
@Data
public class StudentExcelEntity extends BaseRowModel {
    private static final long serialVersionUID = 1L;

    //  主键ID
//    @ExcelProperty(value="学生姓名",index=0)
//    private Integer sid;
    //  学生姓名
    @ExcelProperty(value="学生姓名",index=1)
    private String sname;
    //  性别；0：男；1：女
    @ExcelProperty(value="性别",index=2)
    private String sex;
    //  出生日期
    @ExcelProperty(value="出生日期",index=3)
    private String birthday;
    //  民族
    @ExcelProperty(value="民族",index=4)
    private String nation;
    //    联系方式
//    private String phone;
    //    备注
    @ExcelProperty(value="备注",index=5)
    private String remark;
}
