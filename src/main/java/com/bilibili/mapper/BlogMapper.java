package com.bilibili.mapper;

import com.bilibili.pojo.Blog;
import com.bilibili.pojo.BlogQuery;
import com.bilibili.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogMapper {
    Blog getBlog(Long id);

    List<BlogQuery> listBlog(BlogQuery blog);

    List<Blog> listAllBlog();

    boolean saveBlog(Blog blog);

    boolean updateBlog(Blog blog);

    boolean deleteBlog(Long id);

    List<Blog> listBlogByType(Long id);

    List<Blog> listBlogByTag(Long id);

    List<Blog> getAllRecommendBlog();

    List<Blog> searchBlog(String query);

    List<String> findGroupYear();

    List<Blog> findByYear(String year);
}
