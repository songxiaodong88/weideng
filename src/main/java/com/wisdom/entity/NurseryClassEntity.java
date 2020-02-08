package com.wisdom.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 幼小班/幼小衔接班
 *
 * @author 8888
 * @date 2020-01-23 23:33:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("NURSERY_CLASS")
public class NurseryClassEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //  幼小班ID
//    @TableId
    private Integer ncId;
    //  班级ID
    private Integer classId;
    //  学生ID
    private Integer stuId;
    //  课程ID
    private Integer courseId;
    //  缴费金额
    private String total;
    //  缴费方式
    private String paymentMethod;
    //  课时/月
    private String classHour;
    //  截止时间
    private String periodOfValidity;
    //  缴费时间
    private String payTime;
    //  联系方式
    private String phone;
    //  单价/月
    private Double price;
    //  八月
    private Integer august;
    //  九月
    private Integer september;
    //  十月
    private Integer october;
    //  十一月
    private Integer november;
    //  十二月
    private Integer december;
    //  一月
    private Integer january;
    //  二月
    private Integer february;
    //  三月
    private Integer march;
    //  四月
    private Integer april;
    //  五月
    private Integer may;
    //  六月
    private Integer june;
    //  七月
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
    //  年度
    private Integer year;
    //  备忘
    private String memo;
    //  删除状态，1：正常；0：删除
    private Integer status;
}
