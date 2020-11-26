package com.bilibili.service;

import com.bilibili.mapper.UserMapper;
import com.bilibili.pojo.User;
import com.bilibili.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;

    @Override
    public User checkUser(String username, String password) {
        return userMapper.getUser(username, MD5Utils.code(password));
    }
}
