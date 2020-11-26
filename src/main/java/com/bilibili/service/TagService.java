package com.bilibili.service;

import com.bilibili.pojo.Tag;
import com.bilibili.pojo.Type;

import java.util.List;

public interface TagService {
    boolean saveTag(Tag tag);

    Tag getTag(Long id);

    List<Tag> ListTag();

    boolean updateTag(Tag tag);

    boolean deleteTag(Long id);

    Tag getTagByName(String name);

    List<Tag> ListTag(String ids);

    List<Tag> ListTagTop();
}
