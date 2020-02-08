package com.wisdom.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 班级
 *
 * @author 8888
 * @date 2020-01-17 23:19:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("CLASSES")
public class ClassesEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //  班级主键ID
    @TableId
    private Integer classId;
    //  数据查询后的序号
    @TableField(exist = false)
    private Integer indexId;
    //  班级名称
    private String cname;
    //    上课时间
    private String schooltime;
    //    课程类别
    private String category;
    //  课程ID
    private Integer courseId;
}
