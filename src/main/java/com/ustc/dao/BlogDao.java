package com.ustc.dao;

import com.ustc.pojo.Blog;
import com.ustc.pojo.BlogAndTag;
import com.ustc.pojo.BlogQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author luoxing
 * @create 2021-03-25 20:49
 */
@Mapper
@Repository
public interface BlogDao {

    Blog getBlogById(Long id);

    //获取详细的博客，用来前端展示
    Blog getDetailBolg(Long id);

    //获取后台博客展示列表
    List<Blog> listBlog();

    //获取首页展示博客列表
    List<Blog> listIndexBlog();

    //按条件查询博客
    List<Blog> searchAllBlog(BlogQuery blogQuery);

    //首页搜索博客
    List<Blog> listBlogByQuery(String query);

    //根据分类id查询博客
    List<Blog> listBlogByTypeId(Long typeId);

    //根据分类id查询博客
    List<Blog> listBlogByTagId(Long tagId);

    //获取推荐的博客
    List<Blog> listRecommendBlog();

    //根据年份查询该年份的博客
    List<Blog> findBlogByYear(String year);

    //查询所有博客的年份（去掉重复）
    List<String> findYearOfBlog();

    //发布的博客条数
    int countBlog();

    void updateViews(Long id);

    void saveBlog(Blog blog);

    int updateBlog(Blog blog);

    void deleteBlogById(Long id);

    void saveBlogAndTag(BlogAndTag blogAndTag);

    void deleteBTbyBlogId(Long blogId);


}
