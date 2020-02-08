package com.wisdom.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 实体
 *
 * @author 8888
 * @date 2020-01-17 23:31:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("USERS")
public class UsersEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //  主键ID
    @TableId
    private Integer uid;
    //  用户名
    private String username;
    //  密码
    private String password;
    //  用户角色
    private Integer userrole;
    //  用户头像照片
    private String photo;
    //  创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Timestamp createtime;
}
