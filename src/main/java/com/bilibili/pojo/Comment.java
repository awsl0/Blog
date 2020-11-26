package com.bilibili.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
public class Comment {
    private Long id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;
    private Date createTime;

    private Blog blog;
    private Long blogId;
    private List<Comment> replyComments = new ArrayList<>();
    private Comment parentComment;
    private Long parentCommentId;
    private String parentNickname;

    private boolean adminComment=false;//是否是管理员
}
