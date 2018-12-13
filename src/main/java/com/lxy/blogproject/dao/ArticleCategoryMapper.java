package com.lxy.blogproject.dao;

import com.lxy.blogproject.entity.ArticleCategory;
import com.lxy.blogproject.entity.ArticleCategoryExample;
import java.util.List;

public interface ArticleCategoryMapper {
    int insert(ArticleCategory record);

    int insertSelective(ArticleCategory record);

    List<ArticleCategory> selectByExample(ArticleCategoryExample example);
}