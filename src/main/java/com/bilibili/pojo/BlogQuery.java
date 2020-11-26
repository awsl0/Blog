package com.bilibili.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class BlogQuery {
    private Long id;
    private String title;
    private Date updateTime;
    private Boolean recommend;
    private Boolean published;
    private Long typeId;
    private Type type;
}
