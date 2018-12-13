package com.lxy.blogproject.dao;

import com.lxy.blogproject.entity.Comment;
import com.lxy.blogproject.entity.CommentExample;
import java.util.List;

public interface CommentMapper {
    int insert(Comment record);

    int insertSelective(Comment record);

    List<Comment> selectByExample(CommentExample example);
}