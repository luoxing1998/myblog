package com.ustc.controller.admin;

import com.ustc.pojo.User;
import com.ustc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author luoxing
 * @create 2021-03-24 20:27
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;
    /*
    * 跳转到登录页面
    * */
    @GetMapping()
    public String loginPage(){
        return "admin/login";
    }

    /*
    * 执行登录验证
    * */
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        User user = userService.checkUser(username,password);
        if(user!=null){
            user.setPassword(null);//不要将密码带到前端
            session.setAttribute("user",user);
            return "admin/index";
        }else{
            //因为是重定向，所以不能用Model传递信息，要用RedirectAttributes
            attributes.addFlashAttribute("message","用户名或者密码错误");
            return "redirect:/admin";//验证失败重定向到登录页面
        }
    }

    /*
    * 执行登出业务
    * */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }

}
