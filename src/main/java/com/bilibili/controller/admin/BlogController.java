package com.bilibili.controller.admin;

import com.bilibili.pojo.Blog;
import com.bilibili.pojo.BlogQuery;
import com.bilibili.pojo.User;
import com.bilibili.service.BlogService;
import com.bilibili.service.TagService;
import com.bilibili.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    BlogService blogService;
    @Autowired
    TypeService typeService;
    @Autowired
    TagService tagService;

    @GetMapping("/blogs")
    public String blogs(Model model, BlogQuery blog, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        String orderBy = "update_time desc";
        PageHelper.startPage(pageNum,5,orderBy);
        List<Blog> list = blogService.listBlog();
        PageInfo<Blog> page= new PageInfo<Blog>(list);
        model.addAttribute("page",page);
        model.addAttribute("types",typeService.ListType());
        return "admin/blogs";
    }

    @PostMapping("/blogs/search")
    public String search(Model model,BlogQuery blog,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,5);
        List<BlogQuery> list = blogService.listBlog(blog);
        PageInfo<BlogQuery> page= new PageInfo<BlogQuery>(list);
        model.addAttribute("page",page);
        return "admin/blogs :: blogList";
    }

    @GetMapping("/blogs/input")
    public String input(Model model){
        model.addAttribute("types",typeService.ListType());
        model.addAttribute("tags",tagService.ListTag());
        return "admin/blogs-input";
    }

    @PostMapping("/blogs")
    public String post(Blog blog, HttpSession session, RedirectAttributes attributes){
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.ListTag(blog.getTagIds()));
        boolean flag = blogService.saveBlog(blog);
        if (flag) {
            attributes.addFlashAttribute("message", "操作成功！");
        } else {
            attributes.addFlashAttribute("message", "操作失败！");
        }
        return "redirect:/admin/blogs";
    }

    @GetMapping("/blogs/{id}/update")
    public String updatePage(Model model, @PathVariable Long id){
        model.addAttribute("blog",blogService.getBlog(id));
        model.addAttribute("types",typeService.ListType());
        model.addAttribute("tags",tagService.ListTag());
        return "admin/blogs-update";
    }

    @PostMapping("/blogs/update")
    public String updateBlog(Blog blog,RedirectAttributes attributes){
        blog.setType(typeService.getType(blog.getTypeId()));
        blog.setTags(tagService.ListTag(blog.getTagIds()));
        boolean flag = blogService.updateBlog(blog);
        if (flag) {
            attributes.addFlashAttribute("message", "操作成功！");
        } else {
            attributes.addFlashAttribute("message", "操作失败！");
        }
        return "redirect:/admin/blogs";
    }

    @GetMapping("/blogs/{id}/delete")
    public String delete(RedirectAttributes attributes, @PathVariable Long id){
        boolean flag = blogService.deleteBlog(id);
        if (flag) {
            attributes.addFlashAttribute("message", "删除成功！");
        } else {
            attributes.addFlashAttribute("message", "删除失败！");
        }
        return "redirect:/admin/blogs";
    }
}
