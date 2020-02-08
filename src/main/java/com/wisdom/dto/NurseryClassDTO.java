package com.wisdom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 幼儿班DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NurseryClassDTO implements Serializable {

    //  查询结果后的序号
    private Integer indexId;
    //  主键ID
    private Integer ncId;
    //  学生ID
    private Integer stuId;
    //  班级ID
    private Integer classId;
    //  课程ID
    private Integer courseId;
    //  班级名称
    private String cname;
    //   上课时间
    private String schooltime;
    //   姓名
    private String sname;
    //  总课次数
    private Integer classHour;
    //  学费总额
    private String total;
    //  有效期
    private String periodOfValidity;
    //  缴费日期
    private String payTime;
    //  缴费方式
    private String paymentMethod;
    //  联系方式
    private String phone;
    //  单价/月
    private Double price;
    //  8月消课
    private Integer august;
    //  9月消课
    private Integer september;
    //  10月消课
    private Integer october;
    //  11月消课
    private Integer november;
    //  12月消课
    private Integer december;
    //  1月消课
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
    //  课外班
    private String interestClasses;
    //  书本费
    private Double bookPrice;
    //  小饭桌
    private Double smallDiningTable;
    //  监控
    private Double monitoring;
    //  接送费
    private Double pickUp;
    //  备注
    private String remark;
    //  学平险
    private String studentsSafetyInsurance;
    //  档案
    private String record;
    //  协议
    private String protocol;
    //  备忘
    private String memo;
    //   学费总额统计
    private Double sumTotal;
    //   8月课时统计
    private Integer sumBa;
    //   9月课时统计
    private Integer sumJiu;
    //   10月课时统计
    private Integer sumShi;
    //  11月课时统计
    private Integer sumShy;
    //  12月课时统计
    private Integer sumShe;
    //  1月课时统计
    private Integer sumYi;
    //  删除状态，1：正常；0：删除
    private Integer status;


}
