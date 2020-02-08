package com.wisdom.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 *
 * @author 8888
 * @date 2020-01-17 23:27:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("COST_DETAILS")
public class CostDetailsEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //  费用详情ID
    @TableId
    private Integer cdId;
    //  学生ID
    private Integer stuId;
    //  班级ID
    private Integer classId;
    //  课程ID
    private Integer courseId;
    //  总费用
    private Double total;
    //  课次单价
    private Double price;
    //  学费形式
    private String tuitionForm;
    //  缴费方式
    private String paymentMethod;
    //  缴费时间
    private Date payTime;
    //  总课时
    private Integer classHour;
    //    有效期
    private Date periodOfValidity;
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
    //  八月
    private Integer august;
    //  九月
    private Integer september;
    //  十月
//    private BigDecimal october;
    private String october;
    //  十一月
//    private BigDecimal november;
    private String november;
    //  十二月
    private Integer december;
    //  书费
    private Double bookPrice;
    //  小饭桌
    private Double smallDiningTable;
    //  监控
    private Double monitoring;
    //  接送费
    private Double pickUp;
    //  备注
    private String remark;
    //  备忘
    private String memo;
    //    年度
    private Integer year;
    //  删除状态，1：正常；0：删除
    private Integer status;
}
