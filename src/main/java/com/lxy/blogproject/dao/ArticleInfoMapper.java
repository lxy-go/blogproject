package com.lxy.blogproject.dao;

import com.lxy.blogproject.entity.ArticleInfo;
import com.lxy.blogproject.entity.ArticleInfoExample;
import java.util.List;

public interface ArticleInfoMapper {
    int insert(ArticleInfo record);

    int insertSelective(ArticleInfo record);

    List<ArticleInfo> selectByExample(ArticleInfoExample example);
}