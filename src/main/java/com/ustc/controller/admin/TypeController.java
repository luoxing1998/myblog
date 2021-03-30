package com.ustc.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ustc.pojo.Type;
import com.ustc.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author luoxing
 * @create 2021-03-24 22:31
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;
    /*
    * 跳转到types页面
    * */
    @GetMapping("/types")
    public String types(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum, Model model){
        PageHelper.startPage(pagenum,5);
        List<Type> allType = typeService.listType();
        //分页
        PageInfo<Type> pageInfo = new PageInfo<>(allType);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/types";
    }

    /*
    * 跳转到新增分类页面
    * */
    @GetMapping("/types/input")
    public String toInputPage(Model model){
        model.addAttribute("type",new Type());//new一个空Type对象给前端显示
        return "admin/types-input";
    }

    /*
    * 编辑分类
    * */
    @GetMapping("/types/{id}/input")
    public String toEditPage(@PathVariable Long id,Model model){
        Type type = typeService.getTypeById(id);
        model.addAttribute("type",type);
        return "admin/types-input"; //新增修改共用一个页面
    }

    /*
    * 新增分类
    *前端传来的name值会自动封装到Type对象中
    * */
    @PostMapping("/types")
    public String addType(Type type,RedirectAttributes attributes,Model model){
        Type t = typeService.getTypeByName(type.getName());
        if(t!=null){//要添加的分类已经存在
            model.addAttribute("message","不能添加重复分类");
            return "admin/types-input";
        }else{
            typeService.saveType(type);
            attributes.addFlashAttribute("message","添加成功");
        }
        return "redirect:/admin/types";

    }
    /*
    * 编辑分类
    * */
    @PostMapping("/types/{id}")
    public String editType(@PathVariable Long id,Type type,RedirectAttributes attributes,Model Model){
        Type t = typeService.getTypeByName(type.getName());
        if(t!=null){//要修改的分类已经存在
            Model.addAttribute("message","不能添加重复分类");
            return "admin/types-input";
        }else{
            typeService.updateType(id,type);
            attributes.addFlashAttribute("message","修改成功");
        }
        return "redirect:/admin/types";

    }


    /*
    * 删除分类
    * */
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        typeService.deleteTypeById(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }

}
