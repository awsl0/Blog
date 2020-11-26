package com.bilibili.service;

import com.bilibili.mapper.CommentMapper;
import com.bilibili.pojo.Comment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    BlogService blogService;

    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        List<Comment> comments = commentMapper.listCommentByBlogId(blogId);
        for(Comment comment : comments){
            Long id = comment.getId();
            String parentNickname1 = comment.getNickname();
            List<Comment> childComments = commentMapper.listCommentByParentId(blogId,id);
//            查询出子评论
            combineChildren(blogId, childComments, parentNickname1);
            comment.setReplyComments(tempReplys);
            tempReplys = new ArrayList<>();
            comment.setParentComment(commentMapper.getCommentById(comment.getParentCommentId()));
        }
        return comments;
    }

    @Override
    public boolean saveComment(Comment comment) {
        comment.setCreateTime(new Date());
        Long parentId = comment.getParentComment().getId();
        if (parentId!=-1){
            comment.setParentComment(commentMapper.getCommentById(parentId));
        }else {
            comment.setParentComment(null);
        }
        comment.setBlog(blogService.getBlog(comment.getBlog().getId()));
        return commentMapper.saveComment(comment);
    }

    private void combineChildren(Long blogId, List<Comment> childComments, String parentNickname1) {
//        判断是否有一级子评论
        if(childComments.size() > 0){
//                循环找出子评论的id
            for(Comment childComment : childComments){
                String parentNickname = childComment.getNickname();
                childComment.setParentNickname(parentNickname1);
                tempReplys.add(childComment);
                Long childId = childComment.getId();
//                    查询出子二级评论
                recursively(blogId, childId, parentNickname);
            }
        }
    }

    private void recursively(Long blogId, Long childId, String parentNickname1) {
//        根据子一级评论的id找到子二级评论
        List<Comment> replayComments = commentMapper.findByBlogIdAndReplayId(blogId,childId);

        if(replayComments.size() > 0){
            for(Comment replayComment : replayComments){
                String parentNickname = replayComment.getNickname();
                replayComment.setParentNickname(parentNickname1);
                Long replayId = replayComment.getId();
                tempReplys.add(replayComment);
                recursively(blogId,replayId,parentNickname);
            }
        }
    }
}
