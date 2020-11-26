package com.bilibili.service;

import com.bilibili.pojo.User;

public interface UserService {
    User checkUser(String username,String password);
}
