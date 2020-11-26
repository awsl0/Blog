package com.bilibili.controller;

import com.bilibili.pojo.Blog;
import com.bilibili.pojo.Tag;
import com.bilibili.service.BlogService;
import com.bilibili.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static java.util.Comparator.comparing;

@Controller
public class TagShowController {
    @Autowired
    TagService tagService;
    @Autowired
    BlogService blogService;

    @GetMapping("/tags/{id}")
    public String types(@PathVariable Long id, Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        List<Tag> tags = tagService.ListTagTop();
        tags.sort(comparing(Tag::getBlogNum).reversed());
        if (id==-1)
            id=tags.get(0).getId();
        PageHelper.startPage(pageNum,8);
        Tag t = tagService.getTag(id);
        List<Blog> list = blogService.listBlogByTag(t);
        PageInfo<Blog> page= new PageInfo<Blog>(list);
        model.addAttribute("page",page);
        model.addAttribute("tags",tags);
        model.addAttribute("activeTagId", id);
        return "tags";
    }
}
