package com.ustc.service.Impl;

import com.ustc.dao.UserDao;
import com.ustc.pojo.User;
import com.ustc.service.UserService;
import com.ustc.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author luoxing
 * @create 2021-03-24 20:25
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Override
    public User checkUser(String username, String password) {
        return userDao.checkUser(username, MD5Utils.code(password));
    }
}
