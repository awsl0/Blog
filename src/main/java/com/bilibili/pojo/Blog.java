package com.bilibili.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class Blog {
    private Long id;
    private String title;//标题
    private String content;//内容
    private String firstPicture;//首图
    private String flag;//标记
    private Integer views;//浏览次数
    private boolean appreciation;//赞赏开启
    private boolean shareStatement;//版权开启
    private boolean commentabled;//评论开启
    private boolean published;//发布
    private boolean recommend;//是否推荐
    private Date createTime;//创建时间
    private Date updateTime;//更新时间
    private String description;//博客描述

    private Type type;
    private Long typeId;
    private String tagIds;
    private List<Tag> tags=new ArrayList<>();
    private User user;
    private Long userId;
    private List<Comment> comments=new ArrayList<>();

}
