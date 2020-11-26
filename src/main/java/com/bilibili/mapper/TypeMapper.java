package com.bilibili.mapper;

import com.bilibili.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TypeMapper {
    boolean saveType(Type type);

    Type getType(Long id);

    List<Type> ListType();

    boolean updateType(Type type);

    boolean deleteType(Long id);

    Type getTypeByName(String name);
}
