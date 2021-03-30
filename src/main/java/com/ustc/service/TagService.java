package com.ustc.service;

import com.ustc.pojo.Tag;

import java.util.List;

/**
 * @author luoxing
 * @create 2021-03-25 13:31
 */
public interface TagService {

    void saveTag(Tag tag);

    Tag getTagById(Long id);

    Tag getTagByName(String name);

    List<Tag> listTag();

    //获取所有博客的分类
    List<Tag> listBlogTag();

    List<Tag> listTagByString(String ids);

    int updateTag(Long id,Tag tag);

    void deleteTagById(Long id);
}
