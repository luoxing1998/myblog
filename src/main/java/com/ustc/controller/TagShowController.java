package com.ustc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ustc.pojo.Blog;
import com.ustc.pojo.Tag;
import com.ustc.pojo.Type;
import com.ustc.service.BlogService;
import com.ustc.service.TagService;
import com.ustc.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;

/**
 * @author luoxing
 * @create 2021-03-06 11:10
 */
@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{tagId}")
    public String types(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum,
                        @PathVariable Long tagId, Model model) {
        List<Tag> tags = tagService.listBlogTag(); //按照博客数量排序取出所有的标签
        System.out.println(tags.size());
        for (Tag tag : tags) {
            System.out.println(tag);
            tag.setNum(); //给每一种标签的博客数赋值
        }
        tags.sort(Comparator.comparing(Tag::getNum).reversed());//按博客数量从高到低排序
        if (tagId == -1) {
            tagId = tags.get(0).getId(); //初始情况，默认选择博客数量最多的标签
        }
        model.addAttribute("tags", tags);

        PageHelper.startPage(pagenum,6);
        List<Blog> blogs = blogService.listBlogByTagId(tagId);//通过标签查询博客
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTagId", tagId); //将选中的分类id返回给前端
        return "tags";
    }
}
