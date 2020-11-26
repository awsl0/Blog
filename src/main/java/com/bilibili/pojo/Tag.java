package com.bilibili.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Tag {
    private Long id;
    private String name;

    private List<Blog> blogs=new ArrayList<>();
    private Long blogNum;
}
