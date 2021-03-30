package com.ustc.service;

import com.ustc.pojo.User;

/**
 * @author luoxing
 * @create 2021-03-24 20:20
 */
public interface UserService {
    User checkUser(String username,String password);
}
