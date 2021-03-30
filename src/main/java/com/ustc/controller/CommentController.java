package com.ustc.controller;

import com.ustc.pojo.Comment;
import com.ustc.pojo.User;
import com.ustc.service.BlogService;
import com.ustc.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * @author luoxing
 * @create 2021-03-28 15:59
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;

    @GetMapping("/comments/{blogId}")  //展示留言
    public String comments(@PathVariable Long blogId, Model model){
        model.addAttribute("comments", commentService.listCommentByBlogId(blogId));
        model.addAttribute("blog", blogService.getBlogAndConvert(blogId));
        return "blog :: commentList";
    }

    @PostMapping("/comments")
    public String post(Comment comment , HttpSession session){
        Long blogId = comment.getBlog().getId();
        comment.setBlog(blogService.getBlogAndConvert(blogId));
        comment.setBlogId(blogId);
        User user = (User)session.getAttribute("user");
        if(user != null){ //评论人是管理员
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        }else {
            comment.setAvatar(avatar);
        }

        commentService.saveComment(comment);
        System.out.println(1);
        return "redirect:/comments/"+blogId; //提交完评论，保存之后，重定向，再返回commentList片段
    }
}
