package com.wisdom.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 学生信息表
 *
 * @author 8888
 * @date 2020-01-17 23:30:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("STUDENT")
public class StudentEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //  主键ID
    @TableId
    private Integer sid;
    //  学生姓名
    private String sname;
    //  性别；0：男；1：女
    private Integer sex;
    //  出生日期
    private Date birthday;
    //  民族
    private String nation;
    //    联系方式
    private String phone;
    //    备注
    private String remark;
    //  删除状态，1：正常；0：删除
    private Integer status;
}
