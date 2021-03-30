package com.ustc.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ustc.pojo.Tag;
import com.ustc.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author luoxing
 * @create 2021-03-25 13:57
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    /*
     * 跳转到tags页面
     * */
    @GetMapping("/tags")
    public String types(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum, Model model){
        PageHelper.startPage(pagenum,5);
        List<Tag> allTag = tagService.listTag();
        //分页
        PageInfo<Tag> pageInfo = new PageInfo<>(allTag);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/tags";
    }

    /*
    * 跳转到新增页面
    * */
    @GetMapping("/tags/input")
    public String toInputPage(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";
    }
    /*
     * 编辑标签
     * */
    @GetMapping("/tags/{id}/input")
    public String toEditPage(@PathVariable Long id, Model model){
        Tag tag = tagService.getTagById(id);
        model.addAttribute("tag",tag);
        return "admin/tags-input"; //新增修改共用一个页面
    }

    /*
     * 新增标签
     *前端传来的name值会自动封装到Type对象中
     * */
    @PostMapping("/tags")
    public String addTag(Tag tag,RedirectAttributes attributes,Model model){
        Tag t = tagService.getTagByName(tag.getName());
        if(t!=null){//要添加的分类已经存在
            model.addAttribute("message","不能添加重复标签");
            return "admin/tags-input";
        }else{
            tagService.saveTag(tag);
            attributes.addFlashAttribute("message","添加成功");
        }
        return "redirect:/admin/tags";

    }
    /*
     * 编辑标签
     * */
    @PostMapping("/tags/{id}")
    public String editTag(@PathVariable Long id, Tag tag, RedirectAttributes attributes, Model Model){
        Tag t = tagService.getTagByName(tag.getName());
        if(t!=null){//要修改的分类已经存在
            Model.addAttribute("message","不能添加重复标签");
            return "admin/tags-input";
        }else{
            tagService.updateTag(id,tag);
            attributes.addFlashAttribute("message","修改成功");
        }
        return "redirect:/admin/tags";

    }

    /*
    * 删除标签
    * */
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        tagService.deleteTagById(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tags";
    }
}
