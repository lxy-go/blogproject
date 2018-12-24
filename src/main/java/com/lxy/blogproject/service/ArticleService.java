package com.lxy.blogproject.service;

import com.lxy.blogproject.dto.ArticleDTO;
import com.lxy.blogproject.entity.ArticleCategory;
import com.lxy.blogproject.entity.ArticleContent;
import com.lxy.blogproject.entity.ArticleInfo;
import com.lxy.blogproject.entity.CategoryInfo;
import com.lxy.blogproject.form.ArticleForm;

import java.util.List;

public interface ArticleService {

    public List<ArticleDTO> getAllArticleInfo();

    public ArticleInfo getArticleInfoById(Long id);

    public List<CategoryInfo> getCategorysByArticleId(Long articleId);

    public ArticleContent getContentByArticleId(Long articleId);

    public void deleteArticleById(Long id);

    public void save(ArticleForm articleForm);

    public void update(ArticleForm articleForm);
}
