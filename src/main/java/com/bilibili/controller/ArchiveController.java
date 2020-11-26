package com.bilibili.controller;

import com.bilibili.pojo.Blog;
import com.bilibili.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ArchiveController {
    @Autowired
    BlogService blogService;

    @GetMapping("/archives")
    public String archives(Model model){
        List<Blog> list = blogService.listBlog();
        model.addAttribute("blogCount",list.size());
        model.addAttribute("archiveMap",blogService.archiveBlog());
        return "archives";
    }
}
