package com.ustc.controller.admin;

import com.ustc.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author luoxing
 * @create 2021-03-29 16:40
 */
@Controller
public class ArchivesController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/archives")
    public String archives(Model model){
        model.addAttribute("archiveMap",blogService.archiveBlog()); //按年份分类好的博客
        model.addAttribute("blogCount",blogService.countBlog()); //所有博客的条数
        return "archives";
    }
}
