package com.lxy.blogproject.dao;

import com.lxy.blogproject.entity.ArticleInfo;
import com.lxy.blogproject.util.MyMapper;

import java.util.List;

public interface ArticleInfoMapper extends MyMapper<ArticleInfo> {
    public List<ArticleInfo> getLastArticle();
    public List<ArticleInfo> getLastAllArticle();

}