package com.wisdom.service;

import com.wisdom.entity.UsersEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsersEntityService {

    //  登录
    UsersEntity login(String username,String password);

    //    添加用户
    int addUsers(UsersEntity usersEntity);

    //    修改用户
    int updateUsers(UsersEntity usersEntity);

    //    删除用户
    int delUsers(Integer uid);

    //  查询所有用户
    List<UsersEntity> queryAllUsers();

    //  根据用户ID查看用户详情
    UsersEntity detailUserById(Integer uid);

    //    根据用户名查询用户是否存在
    UsersEntity detailUserByUsername(String username);

}
