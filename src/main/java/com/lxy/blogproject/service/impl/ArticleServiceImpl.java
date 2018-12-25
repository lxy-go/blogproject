package com.lxy.blogproject.service.impl;

import com.lxy.blogproject.dao.*;
import com.lxy.blogproject.dto.ArticleDTO;
import com.lxy.blogproject.entity.ArticleCategory;
import com.lxy.blogproject.entity.ArticleContent;
import com.lxy.blogproject.entity.ArticleInfo;
import com.lxy.blogproject.entity.CategoryInfo;
import com.lxy.blogproject.form.ArticleForm;
import com.lxy.blogproject.service.ArticleService;
import com.lxy.blogproject.util.SnowFlake;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    CategoryInfoMapper categoryInfoMapper;


    /**
     * 新增文章
     * @param articleForm
     */
    @Override
    public void save(ArticleForm articleForm) {

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = dateFormat.format(date);//更新时间ModifiedBy
        articleForm.setModifiedBy(format);
        CategoryInfo categoryInfo = new CategoryInfo();//标签信息
        ArticleCategory articleCategory = new ArticleCategory();//文章标签关联信息
        //基本映射
        ArticleInfo articleInfo = modelMapper.map(articleForm, ArticleInfo.class);
        ArticleContent articleContent = modelMapper.map(articleForm, ArticleContent.class);
        SnowFlake snowFlake = new SnowFlake(2, 3);
        long articleId = snowFlake.nextId();

        long contentId = snowFlake.nextId();

        articleInfo.setId(articleId);
        //articleInfo
        articleInfo.setIsTop(false);
        articleInfo.setTraffic(0);
        articleInfoMapper.insert(articleInfo);

        //categoryInfo&articleCategory
        String categoriesName = articleForm.getName();
        String categoryName[] = categoriesName.split(",");
        for (String categoryString : categoryName) {
            long categoryId = snowFlake.nextId();
            long ArticleCategoryId = snowFlake.nextId();
            categoryInfo.setName(categoryString);
            CategoryInfo categoryInfosql = categoryInfoMapper.selectOne(categoryInfo);
            if (categoryInfosql!=null){
                categoryInfosql.setNumber((byte) (categoryInfosql.getNumber() + 1));
                categoryInfoMapper.updateByPrimaryKeySelective(categoryInfosql);
                articleCategory.setCategoryId(categoryInfosql.getId());
            }else{
                categoryInfo.setNumber((byte) 1);
                categoryInfo.setId(categoryId);
                categoryInfo.setModifiedBy(articleForm.getModifiedBy());
                categoryInfoMapper.insert(categoryInfo);
                articleCategory.setCategoryId(categoryId);
            }
            articleCategory.setId(ArticleCategoryId);
            articleCategory.setArticleId(articleId);
            articleCategory.setModifiedBy(articleContent.getModifiedBy());
            articleCategoryMapper.insert(articleCategory);
        }

        //content&article_content
        articleContent.setId(contentId);
        articleContent.setArticleId(articleId);
        articleContentMapper.insert(articleContent);
    }

    /**
     * 获取所有文章 article-list
     * @return
     */
    @Override
    public List<ArticleDTO> getAllArticleInfo() {
        List<ArticleDTO> res = new ArrayList<>();
        List<ArticleInfo> articleInfos = articleInfoMapper.selectAll();

        for (ArticleInfo articleInfo : articleInfos) {
            res.add(modelMapper.map(articleInfo, ArticleDTO.class));
        }
        return res;
    }

    /**
     * 通过指定ID获取文章
     * @param id
     * @return
     */
    @Override
    public ArticleInfo getArticleInfoById(Long id) {
        ArticleInfo articleInfo = articleInfoMapper.selectByPrimaryKey(id);
        return articleInfo;
    }


    /**
     * 删除一篇文章
     * @param id
     */
    @Override
    public void deleteArticleById(Long id) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String modifyDate = dateFormat.format(date);//更新时间ModifiedBy

        ArticleInfo articleInfo = articleInfoMapper.selectByPrimaryKey(id);
        //Entity
        ArticleCategory articleCategory = new ArticleCategory();
        ArticleContent articleContent = new ArticleContent();
        CategoryInfo categoryInfo = new CategoryInfo();

        Long articleId = articleInfo.getId();
        articleCategory.setArticleId(articleId);
        articleContent.setArticleId(articleId);

        List<ArticleCategory> articleCategories = articleCategoryMapper.select(articleCategory);
        ArticleContent articleContentsql = articleContentMapper.selectOne(articleContent);
        for (ArticleCategory articleCategorysql : articleCategories) {
            Long categoryId = articleCategorysql.getCategoryId();
            categoryInfo.setId(categoryId);
            CategoryInfo categoryInfosql = categoryInfoMapper.selectOne(categoryInfo);
            Byte num = categoryInfosql.getNumber();
            if (num>=1){
                categoryInfosql.setNumber((byte) (num-1));
            }else{
                categoryInfosql.setNumber((byte) 0);
            }
            categoryInfosql.setModifiedBy(modifyDate);
            categoryInfoMapper.updateByPrimaryKeySelective(categoryInfosql);
            articleCategoryMapper.delete(articleCategorysql);
        }

        if(articleContentsql!=null){
            articleContentMapper.delete(articleContentsql);
        }
        articleInfoMapper.deleteByPrimaryKey(id);
    }

    /**
     * 更新一篇文章
     * @param articleForm
     */
    @Override
    public void update(ArticleForm articleForm) {

        ArticleContent articleContent = new ArticleContent();
        ArticleCategory articleCategory = new ArticleCategory();
        CategoryInfo categoryInfo = new CategoryInfo();

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String modifyDate = dateFormat.format(date);//更新时间ModifiedBy
        SnowFlake snowFlake = new SnowFlake(2, 3);

        articleForm.setModifiedBy(modifyDate);
        String categoryNames = articleForm.getName();
        String[] categoryArr = categoryNames.split(",");
        //更新文章信息
        ArticleInfo articleInfo = modelMapper.map(articleForm, ArticleInfo.class);
        articleInfoMapper.updateByPrimaryKeySelective(articleInfo);

        Long articleId = articleInfo.getId();
        //更新内容信息
        articleContent.setArticleId(articleId);
        ArticleContent articleContentsql = articleContentMapper.selectOne(articleContent);
        //如果文章内容更新
        if (!articleContentsql.getContent().equals(articleForm.getContent())){
            articleContentsql.setContent(articleForm.getContent());
            articleContentsql.setModifiedBy(articleForm.getModifiedBy());
            articleContentMapper.updateByPrimaryKeySelective(articleContentsql);
        }
        //更新标签，先检查是否更改，如果更改，先删除，后新增
        String cateNameSQL = "";
        List<ArticleCategory> articleCategoryList = articleCategoryMapper.select(articleCategory);
        for (ArticleCategory category : articleCategoryList) {
            cateNameSQL += categoryInfoMapper.selectByPrimaryKey(category.getCategoryId()).getName()+",";
        }
        String categoryNameSQL = cateNameSQL.substring(0, cateNameSQL.length() - 1);
        //判断是否更新
        if (!categoryNameSQL.equals(categoryNames)){
            //先删除标签信息
            articleCategory.setArticleId(articleId);
            for (ArticleCategory articleCategorysql : articleCategoryList) {
                Long categoryId = articleCategorysql.getCategoryId();
                CategoryInfo categoryInfosql = categoryInfoMapper.selectByPrimaryKey(categoryId);
                Byte number = categoryInfosql.getNumber();
                categoryInfosql.setNumber((byte) (number-1));
                categoryInfoMapper.updateByPrimaryKeySelective(categoryInfosql);
                articleCategoryMapper.delete(articleCategorysql);
            }
            List<CategoryInfo> categoryInfos = categoryInfoMapper.selectAll();
            ArrayList<String> categoryNameArr = new ArrayList<>();
            for (CategoryInfo info : categoryInfos) {
                categoryNameArr.add(info.getName());
            }
            for (String categoryName : categoryArr) {
                long categoryNewId = snowFlake.nextId();
                long articleCategoryNewId = snowFlake.nextId();
                if (categoryNameArr.contains(categoryName)){
                    categoryInfo.setName(categoryName);
                    CategoryInfo categoryInfosql = categoryInfoMapper.selectOne(categoryInfo);
                    Byte number1 = categoryInfosql.getNumber();
                    categoryInfosql.setNumber((byte) (number1+1));
                    categoryInfoMapper.updateByPrimaryKeySelective(categoryInfosql);
                    articleCategory.setCategoryId(categoryInfosql.getId());

                }else{
                    categoryInfo.setId(categoryNewId);
                    categoryInfo.setName(categoryName);
                    categoryInfo.setNumber((byte) 1);
                    categoryInfo.setModifiedBy(modifyDate);
                    categoryInfoMapper.insert(categoryInfo);
                    articleCategory.setCategoryId(categoryNewId);
                }
                articleCategory.setId(articleCategoryNewId);
                articleCategory.setArticleId(articleId);
                articleCategory.setModifiedBy(modifyDate);
                articleCategoryMapper.insert(articleCategory);
            }
        }
    }

    /**
     * 通过文章ID返回类别信息（s）
     * @param articleId
     * @return
     */
    @Override
    public List<CategoryInfo> getCategorysByArticleId(Long articleId) {
        ArrayList<CategoryInfo> categories = new ArrayList<>();
        CategoryInfo categoryInfo = new CategoryInfo();
        ArticleCategory articleCategory = new ArticleCategory();
        articleCategory.setArticleId(articleId);
        List<ArticleCategory> articleCategories = articleCategoryMapper.select(articleCategory);
        for (ArticleCategory articleCategory1 : articleCategories) {
            categoryInfo.setId(articleCategory1.getCategoryId());
            CategoryInfo categoryInfo1 = categoryInfoMapper.selectOne(categoryInfo);
            categories.add(categoryInfo1);
        }
        return categories;
    }

    /**
     * 根据文章ID返回文章详情信息
     * @param articleId
     * @return
     */
    @Override
    public ArticleContent getContentByArticleId(Long articleId) {
        ArticleContent articleContent = new ArticleContent();
        articleContent.setArticleId(articleId);
        ArticleContent articleContentsql = articleContentMapper.selectOne(articleContent);
        return articleContentsql;
    }

}
