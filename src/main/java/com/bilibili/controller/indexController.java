package com.bilibili.controller;

import com.bilibili.config.NotFoundException;
import com.bilibili.pojo.Blog;
import com.bilibili.pojo.BlogQuery;
import com.bilibili.pojo.Tag;
import com.bilibili.pojo.Type;
import com.bilibili.service.BlogService;
import com.bilibili.service.TagService;
import com.bilibili.service.TypeService;
import com.bilibili.util.MarkdownUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static java.util.Comparator.comparing;

@Controller
public class indexController {
    @Autowired
    BlogService blogService;
    @Autowired
    TypeService typeService;
    @Autowired
    TagService tagService;

    @GetMapping("/")
    public String index(Model model, Blog blog, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        String orderBy = "update_time desc";
        PageHelper.startPage(pageNum,8,orderBy);
        List<Blog> list = blogService.listBlog();
        PageInfo<Blog> page= new PageInfo<Blog>(list);
        model.addAttribute("page",page);
        List<Type> type = typeService.ListTypeTop();
        type.sort(comparing(Type::getBlogNum).reversed());
        if (type.size() > 8) {//判断list长度
            List newList = type.subList(0, 8);
            model.addAttribute("types",newList);
        }else {
            model.addAttribute("types",type);
        }
        List<Tag> tags = tagService.ListTagTop();
        tags.sort(comparing(Tag::getBlogNum).reversed());
        if (tags.size() > 8) {//判断list长度
            List newList2 = tags.subList(0, 8);
            model.addAttribute("tags",newList2);
        }else {
            model.addAttribute("tags",tags);
        }
        List<Blog> recommendBlog = blogService.getAllRecommendBlog();
        model.addAttribute("recommendBlogs",recommendBlog);
        return "index";
    }

    @PostMapping("/search")
    public String search(@RequestParam String query, Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        String orderBy = "update_time desc";
        PageHelper.startPage(pageNum,8,orderBy);
        List<Blog> list = blogService.searchBlog(query);
        PageInfo<Blog> page= new PageInfo<Blog>(list);
        model.addAttribute("page",page);
        model.addAttribute("query", query);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id,Model model){
        Blog blog = blogService.getBlog(id);
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(blog.getContent()));
        model.addAttribute("blog",blog);
        return "blog";
    }

    @GetMapping("/footer/newblog")
    public String newBlogs(Model model){
        List<Blog> list = blogService.listBlog();
        if (list.size() >= 3) {//判断list长度
            List newList = list.subList(0, 3);
            model.addAttribute("newblogs",newList);
        }else {
            model.addAttribute("newblogs",list);
        }
        return "_fragments :: newblogList";
    }
}
