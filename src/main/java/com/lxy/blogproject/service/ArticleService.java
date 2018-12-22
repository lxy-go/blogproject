package com.lxy.blogproject.service;

import com.lxy.blogproject.dto.ArticleDTO;
import com.lxy.blogproject.entity.ArticleInfo;
import com.lxy.blogproject.form.ArticleForm;

import java.util.List;

public interface ArticleService {

    public List<ArticleDTO> getAllArticleInfo();

    public ArticleInfo getArticleById(Long id);

    public void deleteArticleById(Long id);

    public void save(ArticleForm articleForm);
}
