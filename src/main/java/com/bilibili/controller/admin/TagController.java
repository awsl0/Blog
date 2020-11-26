package com.bilibili.controller.admin;

import com.bilibili.pojo.Tag;
import com.bilibili.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    TagService tagService;

    @GetMapping("/tags")
    public String tags(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,5);
        List<Tag> tags = tagService.ListTag();
        PageInfo<Tag> page= new PageInfo<Tag>(tags);
        model.addAttribute("page",page);
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String input(){
        return "admin/tags-input";
    }

    @PostMapping("/tags")
    public String addType(Tag tag, RedirectAttributes attributes){
        if (tagService.getTagByName(tag.getName())!=null){
            attributes.addFlashAttribute("message","添加失败，分类重复");
        }else {
            boolean flag = tagService.saveTag(tag);
            if (flag) {
                attributes.addFlashAttribute("message", "添加成功！");
            } else {
                attributes.addFlashAttribute("message", "添加失败！");
            }
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/update")
    public String editinput(@PathVariable("id") Long id, Model model){
        model.addAttribute("tag",tagService.getTag(id));
        return "admin/tags-update";
    }

    @PostMapping("/tags/{id}")
    public String updateType(@PathVariable("id")Long id, Tag tag, RedirectAttributes attributes){
        if (tagService.getTagByName(tag.getName())!=null){
            attributes.addFlashAttribute("message","修改失败，分类重复");
        }else {
            boolean flag = tagService.updateTag(tag);
            if (flag) {
                attributes.addFlashAttribute("message", "修改成功！");
            } else {
                attributes.addFlashAttribute("message", "修改失败！");
            }
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable("id")Long id,RedirectAttributes attributes){
        boolean flag = tagService.deleteTag(id);
        if (flag) {
            attributes.addFlashAttribute("message", "删除成功！");
        } else {
            attributes.addFlashAttribute("message", "删除失败！");
        }
        return "redirect:/admin/tags";
    }
}
