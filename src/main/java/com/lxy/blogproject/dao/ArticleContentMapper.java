package com.lxy.blogproject.dao;

import com.lxy.blogproject.entity.ArticleContent;
import com.lxy.blogproject.entity.ArticleContentExample;
import java.util.List;

public interface ArticleContentMapper {
    int insert(ArticleContent record);

    int insertSelective(ArticleContent record);

    List<ArticleContent> selectByExampleWithBLOBs(ArticleContentExample example);

    List<ArticleContent> selectByExample(ArticleContentExample example);
}