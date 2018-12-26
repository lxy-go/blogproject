package com.lxy.blogproject.service;

import com.lxy.blogproject.dto.ArticleDTO;
import com.lxy.blogproject.entity.*;
import com.lxy.blogproject.form.ArticleForm;

import java.util.List;

public interface ArticleService {

    public List<ArticleDTO> getAllArticleInfo();

    public ArticleInfo getArticleInfoById(Long id);

    public List<CategoryInfo> getCategorysByArticleId(Long articleId);

    public ArticleContent getContentByArticleId(Long articleId);

    public ArticlePicture getPictureUrlByArtId(Long id);

    public void deleteArticleById(Long id);

    public void save(ArticleForm articleForm);

    public void update(ArticleForm articleForm);

    public List<ArticleDTO> getLastArticle();
}
