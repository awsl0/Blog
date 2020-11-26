package com.bilibili.service;

import com.bilibili.mapper.TypeMapper;
import com.bilibili.pojo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService{
    @Autowired
    TypeMapper typeMapper;
    @Autowired
    BlogService blogService;

    @Transactional
    @Override
    public boolean saveType(Type type) {
        return typeMapper.saveType(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        return typeMapper.getType(id);
    }

    @Transactional
    @Override
    public List<Type> ListType() {
        return typeMapper.ListType();
    }

    @Override
    public List<Type> ListTypeTop() {
        List<Type> types = typeMapper.ListType();
        for (Type type : types) {
            type.setBlogs(blogService.listBlogByType(type));
            type.setBlogNum((long) type.getBlogs().size());
        }
        return types;
    }

    @Transactional
    @Override
    public boolean updateType(Type type) {
        return typeMapper.updateType(type);
    }

    @Transactional
    @Override
    public boolean deleteType(Long id) {
        return typeMapper.deleteType(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeMapper.getTypeByName(name);
    }
}
