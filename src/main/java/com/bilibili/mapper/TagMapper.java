package com.bilibili.mapper;

import com.bilibili.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TagMapper {
    boolean saveTag(Tag tag);

    Tag getTag(Long id);

    List<Tag> ListTag();

    boolean updateTag(Tag tag);

    boolean deleteTag(Long id);

    Tag getTagByName(String name);
}
