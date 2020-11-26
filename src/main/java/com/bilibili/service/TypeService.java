package com.bilibili.service;

import com.bilibili.pojo.Type;

import java.util.List;

public interface TypeService {
    boolean saveType(Type type);

    Type getType(Long id);

    List<Type> ListType();

    List<Type> ListTypeTop();

    boolean updateType(Type type);

    boolean deleteType(Long id);

    Type getTypeByName(String name);
}
