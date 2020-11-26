package com.bilibili.service;

import com.bilibili.pojo.Blog;
import com.bilibili.pojo.BlogQuery;
import com.bilibili.pojo.Tag;
import com.bilibili.pojo.Type;

import java.util.List;
import java.util.Map;

public interface BlogService {
    Blog getBlog(Long id);

    List<BlogQuery> listBlog(BlogQuery blog);

    List<Blog> listBlog();

    boolean saveBlog(Blog blog);

    boolean updateBlog(Blog blog);

    boolean deleteBlog(Long id);

    List<Blog> listBlogByType(Type type);

    List<Blog> listBlogByTag(Tag tag);

    List<Blog> getAllRecommendBlog();

    List<Blog> searchBlog(String query);

    Map<String, List<Blog>> archiveBlog();
}
