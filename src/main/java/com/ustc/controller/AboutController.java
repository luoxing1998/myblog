package com.ustc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author luoxing
 * @create 2021-03-06 15:12
 */
@Controller
public class AboutController {

    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
