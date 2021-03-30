package com.ustc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ustc.pojo.Blog;
import com.ustc.pojo.BlogQuery;
import com.ustc.pojo.Tag;
import com.ustc.pojo.Type;
import com.ustc.service.BlogService;
import com.ustc.service.TagService;
import com.ustc.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.List;

/**
 * @author luoxing
 * @create 2021-03-24 18:28
 */
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String index(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum, Model model){
        PageHelper.startPage(pagenum,6); //PageHelper方法后面必须紧跟查询语句
        List<Blog> allBlog = blogService.listIndexBlog();
        PageInfo<Blog> pageInfo = new PageInfo<>(allBlog);
        model.addAttribute("pageInfo",pageInfo);


        List<Type> types = typeService.listBlogType();//获取所有博客的类型
        for (Type type : types) {
            type.setNum(); //给每一种类型的博客数赋值
        }
        types.sort(Comparator.comparing(Type::getNum).reversed());//按博客数量从高到低排序
        model.addAttribute("types",types.subList(0,6));//只取博客数量最多的前6个分类

        //PageHelper.startPage(1,9);
        List<Tag> allTags = tagService.listBlogTag();//获取所有博客对应的标签
        for (Tag tag : allTags) {
            tag.setNum();
        }
        allTags.sort(Comparator.comparing(Tag::getNum).reversed());
        //PageInfo<Tag> tags = new PageInfo<>(allTags);
        model.addAttribute("tags",allTags.subList(0,6));


        PageHelper.startPage(1,6);
        List<Blog> allrecommendBlogs = blogService.listRecommendBlog();//获取推荐博客
        PageInfo<Blog> recommendBlogs = new PageInfo<>(allrecommendBlogs);
        model.addAttribute("recommendBlogs",recommendBlogs);//只取前6条博客
        return "index";
    }

    /*
    * 首页查询博客
    * */
    @GetMapping("/search")
    public String search(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum,
                         @RequestParam String query, Model model){
        PageHelper.startPage(pagenum,6); //PageHelper方法后面必须紧跟查询语句
        List<Blog> allBlog = blogService.listBlogByQuery(query);
        PageInfo<Blog> pageInfo = new PageInfo<>(allBlog);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("query",query);
        return "search";
    }


    /*
    * 跳转到博客详情页
    * */
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id,Model model){
        model.addAttribute("blog",blogService.getBlogAndConvert(id));
        return "blog";
    }

    /*
    * 最新推荐的三条博客
    * */
    @GetMapping("/footer/newblog")
    public String newBlogs(Model model){
        model.addAttribute("newblogs",blogService.listRecommendBlog().subList(0,3)); //拿到推荐的三条博客
        return "_fragments :: newblogList"; //返回到newblogList片段
    }
}
