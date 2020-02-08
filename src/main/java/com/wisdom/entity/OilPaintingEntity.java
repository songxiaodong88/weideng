package com.wisdom.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 油画、素描
 *
 * @author 8888
 * @date 2020-01-26 17:35:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("OIL_PAINTING")
public class OilPaintingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //  主键ID
    @TableId
    private Integer opId;
    //  学生ID
    private Integer stuId;
    //  班级ID
    private Integer classId;
    //  课程ID
    private Integer courseId;
    //  报课时间
    private Date registrationTime;
    //  缴费金额
    private Double total;
    //  报课次数(含赠课)
    private Integer classHour;
    //  赠课次数
    private Integer giveLessons;
    //  每次课费用
    private Double price;
    //  18年11月
    private Integer yibaNovember;
    //  18年12月
    private Integer yibaDecember;
    //  19年1月
    private Integer yijiuJanuary;
    //  19年3月
    private Integer yijiuMarch;
    //  19年4月
    private Integer yijuApril;
    //  19年5月
    private Integer yijiuMay;
    //  19年6月
    private Integer yijiuJune;
    //  19年7月
    private Integer yijiuJuly;
    //  19年9月
    private Integer yijiuSeptember;
    //  19年10月
    private Integer yijiuOctober;
    //  19年11月
    private Integer yijiuNovember;
    //  19年12月
    private Integer yijiuDecember;
    //  缴费时间，0：18年9月前缴费；1：18年9月后缴费；
    private Integer status;
}
