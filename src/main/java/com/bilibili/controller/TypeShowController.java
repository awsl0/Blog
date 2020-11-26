package com.bilibili.controller;

import com.bilibili.pojo.Blog;
import com.bilibili.pojo.BlogQuery;
import com.bilibili.pojo.Type;
import com.bilibili.service.BlogService;
import com.bilibili.service.TypeService;
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
public class TypeShowController {
    @Autowired
    TypeService typeService;
    @Autowired
    BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@PathVariable Long id, Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        List<Type> type = typeService.ListTypeTop();
        type.sort(comparing(Type::getBlogNum).reversed());
        if (id==-1)
            id=type.get(0).getId();
        PageHelper.startPage(pageNum,8);
        Type t = typeService.getType(id);
        List<Blog> list = blogService.listBlogByType(t);
        PageInfo<Blog> page= new PageInfo<Blog>(list);
        model.addAttribute("page",page);
        model.addAttribute("types",type);
        model.addAttribute("activeTypeId", id);
        return "types";
    }
}
