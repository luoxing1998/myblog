package com.ustc.dao;

import com.ustc.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author luoxing
 * @create 2021-03-24 20:21
 */
@Mapper
@Repository
public interface UserDao {
    User checkUser(String username, String password);
}
