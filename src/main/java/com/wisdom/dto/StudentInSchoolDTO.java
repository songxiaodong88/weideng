package com.wisdom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 所有在校学员信息DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentInSchoolDTO implements Serializable {

    //  查询结果后的序号
    private Integer indexId;
    //  主键ID
    private Integer sid;
    //   姓名
    private String sname;
    //  性别
    private Integer sex;
    //  生日
    private Date birthday;
    //  民族
    private String nation;
    //  联系方式
    private String phone;
    //  学费总额
    private String total;
    //  缴费日期
    private String payTime;
    //  学费形式
    private String tuitionForm;
    //  总课时
    private Integer classHour;
    //  班级名称
    private String cname;
    //  截止日期
    private String periodOfValidity;
    //  课时单价
    private Double price;
    //  9月消课
    private Integer september;
    //  10月消课
    private Double october;
    //  11月消课
    private Double november;
    //  12月消课
    private Integer december;
    //  1月
    private Integer january;
    //  2月
    private Integer february;
    //  3月
    private Integer march;
    //  4月
    private Integer april;
    //  5月
    private Integer may;
    //  6月
    private Integer june;
    //  7月
    private Integer july;
    //  8月消课
    private Integer august;
    //  书本费
    private Double bookPrice;
    //  课外班
    private String interestClasses;
    //  小饭桌
    private Double smallDiningTable;
    //  监控
    private Double monitoring;
    //  接送费
    private Double pickUp;
    //  备注
    private String remark;
    //    学费总额统计
    private Double sumTotal;
    //  删除状态，1：正常；0：删除
    private Integer status;


}
