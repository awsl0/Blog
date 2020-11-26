package com.bilibili.service;

import com.bilibili.mapper.TagMapper;
import com.bilibili.pojo.Blog;
import com.bilibili.pojo.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService{
    @Autowired
    TagMapper tagMapper;
    @Autowired
    BlogService blogService;

    @Override
    @Transactional
    public boolean saveTag(Tag tag) {
        return tagMapper.saveTag(tag);
    }

    @Override
    @Transactional
    public Tag getTag(Long id) {
        return tagMapper.getTag(id);
    }

    @Override
    @Transactional
    public List<Tag> ListTag() {
        return tagMapper.ListTag();
    }

    @Override
    @Transactional
    public boolean updateTag(Tag tag) {
        return tagMapper.updateTag(tag);
    }

    @Override
    @Transactional
    public boolean deleteTag(Long id) {
        return tagMapper.deleteTag(id);
    }

    @Override
    @Transactional
    public Tag getTagByName(String name) {
        return tagMapper.getTagByName(name);
    }

    @Override
    @Transactional
    public List<Tag> ListTag(String ids) {
        List<Tag> tags=new ArrayList<>();
        if (!StringUtils.isEmpty(ids)) {
            String[] str=ids.split(",");
            for (String s:str){
                tags.add(tagMapper.getTag(Long.valueOf(s)));
            }
        }
        return tags;
    }

    @Override
    @Transactional
    public List<Tag> ListTagTop() {
        List<Tag> tags = tagMapper.ListTag();
        for (Tag tag : tags) {
            tag.setBlogs(blogService.listBlogByTag(tag));
            tag.setBlogNum((long) tag.getBlogs().size());
        }
        return tags;
    }
}
