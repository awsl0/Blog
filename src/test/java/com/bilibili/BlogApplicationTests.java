package com.bilibili;

import com.bilibili.mapper.BlogMapper;
import com.bilibili.pojo.Blog;
import com.bilibili.service.BlogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {
    @Autowired
    BlogMapper blogMapper;

    @Test
    public void contextLoads() {

    }

}
