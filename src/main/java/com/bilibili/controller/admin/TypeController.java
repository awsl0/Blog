package com.bilibili.controller.admin;

import com.bilibili.pojo.Type;
import com.bilibili.service.TypeService;
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
public class TypeController {
    @Autowired
    TypeService typeService;

    @GetMapping("/types")
    public String types(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,5);
        List<Type> list = typeService.ListType();
        PageInfo<Type> page= new PageInfo<Type>(list);
        model.addAttribute("page",page);
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(){
        return "admin/types-input";
    }

    @PostMapping("/types")
    public String addType(Type type, RedirectAttributes attributes){
        if (typeService.getTypeByName(type.getName())!=null){
            attributes.addFlashAttribute("message","添加失败，分类重复");
        }else {
            boolean flag = typeService.saveType(type);
            if (flag) {
                attributes.addFlashAttribute("message", "添加成功！");
            } else {
                attributes.addFlashAttribute("message", "添加失败！");
            }
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/update")
    public String editinput(@PathVariable("id") Long id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-update";
    }

    @PostMapping("/types/{id}")
    public String updateType(@PathVariable("id")Long id, Type type, RedirectAttributes attributes){
        if (typeService.getTypeByName(type.getName())!=null){
            attributes.addFlashAttribute("message","修改失败，分类重复");
        }else {
            boolean flag = typeService.updateType(type);
            if (flag) {
                attributes.addFlashAttribute("message", "修改成功！");
            } else {
                attributes.addFlashAttribute("message", "修改失败！");
            }
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable("id")Long id,RedirectAttributes attributes){
        boolean flag = typeService.deleteType(id);
        if (flag) {
            attributes.addFlashAttribute("message", "删除成功！");
        } else {
            attributes.addFlashAttribute("message", "删除失败！");
        }
        return "redirect:/admin/types";
    }
}
