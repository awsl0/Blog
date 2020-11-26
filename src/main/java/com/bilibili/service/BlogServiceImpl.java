package com.bilibili.service;

import com.bilibili.mapper.BlogMapper;
import com.bilibili.mapper.TagMapper;
import com.bilibili.mapper.TypeMapper;
import com.bilibili.mapper.UserMapper;
import com.bilibili.pojo.Blog;
import com.bilibili.pojo.BlogQuery;
import com.bilibili.pojo.Tag;
import com.bilibili.pojo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService{
    @Autowired
    BlogMapper blogMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    TagService tagService;
    @Autowired
    TypeMapper typeMapper;

    @Override
    @Transactional
    public Blog getBlog(Long id) {
        return TypeAndTag(blogMapper.getBlog(id));
    }

    @Override
    @Transactional
    public List<BlogQuery> listBlog(BlogQuery blog) {
        if (blog.getTypeId()==null) {
            blog.setTypeId(Long.valueOf(-1));
        }
        return blogMapper.listBlog(blog);
    }

    @Override
    public List<Blog> listBlog() {
        return TypeAndTag(blogMapper.listAllBlog());
    }

    @Override
    @Transactional
    public boolean saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        return blogMapper.saveBlog(blog);
    }

    @Override
    @Transactional
    public boolean updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        return blogMapper.updateBlog(blog);
    }

    @Override
    @Transactional
    public boolean deleteBlog(Long id) {
        return blogMapper.deleteBlog(id);
    }

    @Override
    public List<Blog> listBlogByType(Type type) {
        return TypeAndTag(blogMapper.listBlogByType(type.getId()));
    }

    @Override
    public List<Blog> listBlogByTag(Tag tag) {
        return TypeAndTag(blogMapper.listBlogByTag(tag.getId()));
    }

    @Override
    public List<Blog> getAllRecommendBlog() {
        return blogMapper.getAllRecommendBlog();
    }

    @Override
    public List<Blog> searchBlog(String query) {
        return TypeAndTag(blogMapper.searchBlog(query));
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogMapper.findGroupYear();
        Map<String, List<Blog>> map = new HashMap<>();
        for (String year : years) {
            map.put(year,blogMapper.findByYear(year));
        }
        return map;
    }

    private List<Blog> TypeAndTag(List<Blog> blogs){
        for (Blog blog : blogs) {
            blog.setUser(userMapper.getUserById(blog.getUserId()));
            blog.setType(typeMapper.getType(blog.getTypeId()));
            blog.setTags(tagService.ListTag(blog.getTagIds()));
        }
        return blogs;
    }
    private Blog TypeAndTag(Blog blog){
        blog.setUser(userMapper.getUserById(blog.getUserId()));
        blog.setType(typeMapper.getType(blog.getTypeId()));
        blog.setTags(tagService.ListTag(blog.getTagIds()));
        return blog;
    }
}
