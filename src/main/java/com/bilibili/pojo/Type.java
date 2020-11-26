package com.bilibili.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class Type {
    private Long id;
    private String name;

    private List<Blog> blogs=new ArrayList<>();
    private Long blogNum;
}
