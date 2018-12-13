package com.lxy.blogproject.dao;

import com.lxy.blogproject.entity.ArticleComment;
import com.lxy.blogproject.entity.ArticleCommentExample;
import java.util.List;

public interface ArticleCommentMapper {
    int insert(ArticleComment record);

    int insertSelective(ArticleComment record);

    List<ArticleComment> selectByExample(ArticleCommentExample example);
}