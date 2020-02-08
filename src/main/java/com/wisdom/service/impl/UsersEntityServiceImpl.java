package com.wisdom.service.impl;

import com.wisdom.dao.UsersEntityDao;
import com.wisdom.entity.UsersEntity;
import com.wisdom.service.UsersEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersEntityServiceImpl implements UsersEntityService {

    @Autowired
    private UsersEntityDao usersEntityDao;

    @Override
    public UsersEntity login(String username, String password) {
        return usersEntityDao.login(username,password);
    }

    @Override
    public int addUsers(UsersEntity usersEntity) {
        return usersEntityDao.addUsers(usersEntity);
    }

    @Override
    public int updateUsers(UsersEntity usersEntity) {
        return usersEntityDao.updateUsers(usersEntity);
    }

    @Override
    public int delUsers(Integer uid) {
        return usersEntityDao.delUsers(uid);
    }

    @Override
    public List<UsersEntity> queryAllUsers() {
        return usersEntityDao.queryAllUsers();
    }

    @Override
    public UsersEntity detailUserById(Integer uid) {
        return usersEntityDao.detailUserById(uid);
    }

    @Override
    public UsersEntity detailUserByUsername(String username) {
        return usersEntityDao.detailUserByUsername(username);
    }

}
