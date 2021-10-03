package com.imooc.miaosha.service;

import com.imooc.miaosha.dao.UserDao;
import com.imooc.miaosha.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    UserDao userDao;

    public User getById(int id) {
        return userDao.getById(id);
    }

    @Transactional
    public boolean tx() {
        User u1 = User.builder()
                .id(2)
                .name("ymj")
                .build();
        userDao.insert(u1);
        User u2 = User.builder()
                .id(1)
                .name("ymj")
                .build();
        userDao.insert(u2);

        return true;

    }
}
