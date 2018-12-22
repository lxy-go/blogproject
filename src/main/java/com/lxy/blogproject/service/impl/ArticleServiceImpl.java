package com.lxy.blogproject.service.impl;

import com.lxy.blogproject.dao.ArticleCategoryMapper;
import com.lxy.blogproject.dao.ArticleContentMapper;
import com.lxy.blogproject.dao.ArticleInfoMapper;
import com.lxy.blogproject.dao.ArticlePictureMapper;
import com.lxy.blogproject.dto.ArticleDTO;
import com.lxy.blogproject.entity.ArticleCategory;
import com.lxy.blogproject.entity.ArticleContent;
import com.lxy.blogproject.entity.ArticleInfo;
import com.lxy.blogproject.entity.ArticlePicture;
import com.lxy.blogproject.form.ArticleForm;
import com.lxy.blogproject.service.ArticleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ArticleInfoMapper articleInfoMapper;
    @Autowired
    ArticleCategoryMapper articleCategoryMapper;
    @Autowired
    ArticleContentMapper articleContentMapper;
    @Autowired
    ArticlePictureMapper articlePictureMapper;

    @Override
    public List<ArticleDTO> getAllArticleInfo() {
        List<ArticleDTO> res = new ArrayList<>();
        List<ArticleInfo> articleInfos = articleInfoMapper.selectAll();

        for (ArticleInfo articleInfo : articleInfos) {
            res.add(modelMapper.map(articleInfo, ArticleDTO.class));
        }
        return res;
    }

    @Override
    public ArticleInfo getArticleById(Long id) {
        ArticleInfo articleInfo = articleInfoMapper.selectByPrimaryKey(id);
        return articleInfo;
    }

    @Override
    public void deleteArticleById(Long id) {
        articleInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void save(ArticleForm articleForm) {

        ArticleInfo articleInof = modelMapper.map(articleForm, ArticleInfo.class);
        ArticleContent articleContent = modelMapper.map(articleForm, ArticleContent.class);
        ArticleCategory articleCategory = modelMapper.map(articleForm, ArticleCategory.class);

        articleInfoMapper.insert(articleInof);
        articleContentMapper.insert(articleContent);
        articleCategoryMapper.insert(articleCategory);
    }
}
