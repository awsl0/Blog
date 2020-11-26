package com.bilibili.service;

import com.bilibili.pojo.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    boolean saveComment(Comment comment);
}
