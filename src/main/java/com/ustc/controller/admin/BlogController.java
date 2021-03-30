package com.ustc.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ustc.pojo.Blog;
import com.ustc.pojo.BlogQuery;
import com.ustc.pojo.User;
import com.ustc.service.BlogService;
import com.ustc.service.TagService;
import com.ustc.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author luoxing
 * @create 2021-03-24 21:45
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    /*
    *跳转到博客列表页面
     */
    @GetMapping("/blogs")
    public String blogs(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum, Model model){
        PageHelper.startPage(pagenum,6);
        List<Blog> allBlog = blogService.listBlog();
        PageInfo<Blog> pageInfo = new PageInfo<>(allBlog);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("types",typeService.listType());
        return "admin/blogs";
    }

    /*
    * 按条件查询博客
    * */
    @PostMapping("/blogs/search")
    public String search(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum, BlogQuery blogQuery,Model model){
        PageHelper.startPage(pagenum,6);
        List<Blog> allSearchBlog = blogService.searchAllBlog(blogQuery);
        PageInfo<Blog> pageInfo = new PageInfo<>(allSearchBlog);
        model.addAttribute("pageInfo",pageInfo);
        System.out.println(pageInfo);
        //model.addAttribute("types",typeService.listType());
        return "admin/blogs :: blogList";
    }

    /*
    * 跳转到新增博客页面
    * */
    @GetMapping("/blogs/input")
    public String toInputPage(Model model){
        model.addAttribute("blog",new Blog());
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
        return "admin/blogs-input";
    }

    /*
    * 跳转到修改博客页面
    * */
    @GetMapping("/blogs/{id}/input")
    public String toEditPage(@PathVariable Long id, Model model){
        Blog blog = blogService.getBlogById(id);
        model.addAttribute("blog",blog);
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
        return "admin/blogs-input";
    }

    /*
    *新增博客
    * */
    @PostMapping("/blogs")
    public String addBlog(Blog blog, RedirectAttributes attributes, HttpSession session){
        blog.setUser((User)session.getAttribute("user"));
        blog.setUserId(((User) session.getAttribute("user")).getId());
        blog.setTypeId(blog.getType().getId());
        blog.setTags(tagService.listTagByString(blog.getTagIds()));
        if(blog.getId() == null){
            //id为空表明为新增博客
            blogService.saveBlog(blog);
        }else{
            blogService.updateBlog(blog);
        }
        attributes.addFlashAttribute("message","操作成功");
        return "redirect:/admin/blogs";
    }

    /*
    * 删除博客
    * */
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        blogService.deleteBlogById(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/blogs";
    }



}
