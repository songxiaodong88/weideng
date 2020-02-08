package com.wisdom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 幼小衔接班DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PRESchoolDTO implements Serializable {

    //  查询结果后的序号
    private Integer indexId;
    //  主键ID
    private Integer ncId;
    //  班级名称
    private String cname;
    //   上课时间
    private String schooltime;
    //   姓名
    private String sname;
    //  学费总额
    private Double total;
    //  交费方式
    private String paymentMethod;
    //  截止日期
    private String periodOfValidity;
    //  缴费日期
    private String payTime;
    //  联系方式
    private String phone;
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
    //    学费总额统计
    private Double sumTotal;
    //  删除状态，1：正常；0：删除
    private Integer status;


}
