package com.ustc.dao;

import com.ustc.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author luoxing
 * @create 2021-03-24 22:01
 */
@Mapper
@Repository
public interface TypeDao {

    void saveType(Type type);

    Type getTypeById(Long id);

    Type getTypeByName(String name);

    //获取所有的分类
    List<Type> listType();

    //获取博客的分类
    List<Type> listBlogType();

    int updateType(Type type);

    void deleteTypeById(Long id);
}
