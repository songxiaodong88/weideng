package com.wisdom.excel.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * 课程收费标准
 */
@Data
public class CourseExcelEntity extends BaseRowModel {

    //  课程ID
    private Integer cid;
    //  课程类别
    @ExcelProperty(value="课程类别",index=0)
    private String cname;
    //  单次（元/次）
    @ExcelProperty(value="单次（yuan/次）",index=1)
    private String once;
    //  课程时间
    @ExcelProperty(value="课程时间",index=2)
    private String courseTime;
    //  周频次
    @ExcelProperty(value="周频次",index=3)
    private String weekFrequency;
    //  学期课时
    @ExcelProperty(value="学期课时",index=4)
    private String totalTime;
    //  收费标准
    @ExcelProperty(value="收费标准",index=5)
    private String cost;
    //  班级人数
    @ExcelProperty(value="班级人数",index=6)
    private String classPeople;
    //  备注
    private String remark;
}
