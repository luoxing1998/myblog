package com.ustc.dao;

import com.ustc.pojo.Tag;
import com.ustc.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author luoxing
 * @create 2021-03-25 13:30
 */
@Mapper
@Repository
public interface TagDao {

    void saveTag(Tag tag);

    Tag getTagById(Long id);

    Tag getTagByName(String name);

    List<Tag> listTag();

    //获取所有博客的分类
    List<Tag> listBlogTag();

    int updateTag(Tag tag);

    void deleteTagById(Long id);
}
