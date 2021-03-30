package com.ustc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ustc.pojo.Blog;
import com.ustc.pojo.Type;
import com.ustc.service.BlogService;
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
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{typeId}")
    public String types(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum,
                        @PathVariable Long typeId, Model model) {
        List<Type> types = typeService.listBlogType(); //按照博客数量排序取出所有的分类
        for (Type type : types) {
            type.setNum(); //给每一种类型的博客数赋值
        }
        types.sort(Comparator.comparing(Type::getNum).reversed());//按博客数量从高到低排序
        if (typeId == -1) {
            typeId = types.get(0).getId(); //初始情况，默认选择博客数量最多的分类
        }
        model.addAttribute("types", types);

        PageHelper.startPage(pagenum,6);
        List<Blog> blogs = blogService.listBlogByTypeId(typeId);//通过分类查询博客
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTypeId", typeId); //将选中的分类id返回给前端
        return "types";
    }
}
