package com.ustc.service.Impl;

import com.ustc.NotFoundException;
import com.ustc.dao.BlogDao;
import com.ustc.pojo.Blog;
import com.ustc.pojo.BlogAndTag;
import com.ustc.pojo.BlogQuery;
import com.ustc.pojo.Tag;
import com.ustc.service.BlogService;
import com.ustc.util.MarkDownUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author luoxing
 * @create 2021-03-26 13:03
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Override
    public Blog getBlogById(Long id) {
        Blog  blog=  blogDao.getBlogById(id);
        System.out.println(blog.getContent());
        return blog;
    }

    public Blog getBlogAndConvert(Long id){
        Blog blog = blogDao.getDetailBolg(id);
        if(blog == null){
            throw new NotFoundException("博客不存在");
        }
        blogDao.updateViews(id);
        Blog b = new Blog();

        String content = blog.getContent();
        blog.setContent(MarkDownUtils.markdownToHtmlExtensions(content));  //将Markdown格式转换成html
        return blog;
    }

    @Override
    public List<Blog> listBlog() {
        return blogDao.listBlog();
    }

    @Override
    public List<Blog> listIndexBlog() {
        return blogDao.listIndexBlog();
    }

    @Override
    public List<Blog> listBlogByTypeId(Long typeId) {
        return blogDao.listBlogByTypeId(typeId);
    }

    @Override
    public List<Blog> listBlogByTagId(Long tagId) {
        return blogDao.listBlogByTagId(tagId);
    }

    @Override
    public List<Blog> listBlogByQuery(String query) {
        return blogDao.listBlogByQuery(query);
    }

    @Override
    public List<Blog> listRecommendBlog() {
        return blogDao.listRecommendBlog();
    }

    @Override
    public List<Blog> searchAllBlog(BlogQuery blogQuery) {
        return blogDao.searchAllBlog(blogQuery);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogDao.findYearOfBlog();
        HashMap<String,List<Blog>> map = new HashMap<>();
        for (String year : years) {
            map.put(year,blogDao.findBlogByYear(year));
        }
        return map;
    }

    @Override
    public int countBlog() {
        return blogDao.countBlog();
    }


    @Transactional
    @Override
    public void saveBlog(Blog blog) {
        //新博客需要初始化
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        //将博客和标签的多对多关系存进数据库
        blogDao.saveBlog(blog);
        //保存博客后才能获取自增的id
        Long id = blog.getId();
        List<Tag> tags = blog.getTags();
        for (Tag tag : tags) {
            BlogAndTag blogAndTag = new BlogAndTag(tag.getId(),id);
            blogDao.saveBlogAndTag(blogAndTag);
        }


    }



    @Transactional
    @Override
    public int updateBlog(Blog blog) {
        //更新博客需要重新设置UpdateTime属性
        blog.setUpdateTime(new Date());
        List<Tag> tags = blog.getTags();
        //先t_blog_tags表中的数据，再插入新的数据
        blogDao.deleteBTbyBlogId(blog.getId());
        for (Tag tag : tags) {

            blogDao.saveBlogAndTag(new BlogAndTag(tag.getId(),blog.getId()));

        }
        return blogDao.updateBlog(blog);
    }


    @Override
    public void deleteBlogById(Long id) {
        //t_blog表和t_blog_tags表中的数据都需要删除
        blogDao.deleteBTbyBlogId(id);
        blogDao.deleteBlogById(id);
    }
}
