package com.bilibili.mapper;

import com.bilibili.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {
    List<Comment> listCommentByBlogId(Long blogId);

    boolean saveComment(Comment comment);

    Comment getCommentById(Long id);

    List<Comment> listCommentByParentId(Long blogId,Long parentCommentId);

    List<Comment> findByBlogIdAndReplayId(Long blogId,Long childId);
}
