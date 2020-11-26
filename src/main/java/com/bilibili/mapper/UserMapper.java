package com.bilibili.mapper;

import com.bilibili.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    User getUser(String username,String password);

    User getUserById(Long id);
}
