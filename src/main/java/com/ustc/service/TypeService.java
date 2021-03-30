package com.ustc.service;

import com.ustc.pojo.Type;

import java.util.List;

/**
 * @author luoxing
 * @create 2021-03-24 21:57
 */
public interface TypeService {

    void saveType(Type type);

    Type getTypeById(Long id);

    Type getTypeByName(String name);

    List<Type> listType();

    //获取博客的分类
    List<Type> listBlogType();

    int updateType(Long id,Type type);

    void deleteTypeById(Long id);
}
