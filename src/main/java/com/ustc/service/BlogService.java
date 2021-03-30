package com.ustc.service;

import com.ustc.pojo.Blog;
import com.ustc.pojo.BlogQuery;

import java.util.List;
import java.util.Map;

/**
 * @author luoxing
 * @create 2021-03-26 12:43
 */
public interface BlogService {

    Blog getBlogById(Long id);

    //获取博客后并将博客的内容由MarkDown格式转化为html格式，便于在前端显示
    public Blog getBlogAndConvert(Long id);

    //获取后台展示博客列表
    List<Blog> listBlog();

    //获取首页展示博客列表
    List<Blog> listIndexBlog();


    //根据分类id查询博客
    List<Blog> listBlogByTypeId(Long typeId);

    //根据分类id查询博客
    List<Blog> listBlogByTagId(Long tagId);

    //首页搜索博客
    List<Blog> listBlogByQuery(String query);

    //获取推荐的博客
    List<Blog> listRecommendBlog();

    //按条件查询博客
    List<Blog> searchAllBlog(BlogQuery blogQuery);

    //void updateViews(Long id);
    Map<String,List<Blog>> archiveBlog();  //归档博客

    //发布的博客条数
    int countBlog();

    void saveBlog(Blog blog);

    int updateBlog(Blog blog);

    void deleteBlogById(Long id);
}
