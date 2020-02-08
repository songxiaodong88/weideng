package com.wisdom.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 维登教育课程收费标准
 *
 * @author 8888
 * @date 2020-01-17 23:29:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("COURSE")
public class CourseOldEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //  课程ID
    @TableId
    private Integer cid;
    //  课程类别
    private String cname;
    //  单次（元/次）
    private String once;
    //  课程时间
    private String courseTime;
    //  周频次
    private String weekFrequency;
    //  学期课时
    private String totalTime;
    //  收费标准
    private String cost;
    //  班级人数
    private String classPeople;
    //  备注
    private String remark;
}
