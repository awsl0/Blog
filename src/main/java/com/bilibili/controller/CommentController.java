package com.bilibili.controller;

import com.bilibili.pojo.Comment;
import com.bilibili.pojo.User;
import com.bilibili.service.BlogService;
import com.bilibili.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    BlogService blogService;
    @Value("${comment.avatar}")
    private String avatar;

    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model){
        model.addAttribute("comments",commentService.listCommentByBlogId(blogId));
        return "blog :: commentList";
    }

    @PostMapping("/comments")
    public String post(Comment comment, RedirectAttributes attributes, HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user!=null){
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
            comment.setNickname(user.getNickname());
            comment.setEmail(user.getEmail());
        }else {
            comment.setAvatar(avatar);
        }
        if (commentService.saveComment(comment)){
            attributes.addFlashAttribute("message","评论成功！");
        }else {
            attributes.addFlashAttribute("message","评论失败！");
        }
        return "redirect:/comments/"+comment.getBlog().getId();
    }
}
