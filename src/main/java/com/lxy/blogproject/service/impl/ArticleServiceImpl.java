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
        articleContent.setModifiedBy(articleForm.getModifiedBy());
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

        ArticleInfo articleInfo = articleInfoMapper.selectByPrimaryKey(id);
        //Entity
        ArticleCategory articleCategory = new ArticleCategory();
        ArticleContent articleContent = new ArticleContent();
        CategoryInfo categoryInfo = new CategoryInfo();

        Long articleId = articleInfo.getId();
        articleCategory.setArticleId(articleId);
        articleContent.setArticleId(articleId);

        List<ArticleCategory> categories = articleCategoryMapper.select(articleCategory);
        ArticleContent articleContentsql = articleContentMapper.selectOne(articleContent);
        for (ArticleCategory category : categories) {
            Long categoryId = category.getCategoryId();
            CategoryInfo categoryInfosql = categoryInfoMapper.selectByPrimaryKey(category.getCategoryId());
            categoryInfo.setId(categoryId);
            if (categoryInfosql.getNumber()>=1){
                categoryInfo.setNumber((byte) (categoryInfosql.getNumber()-1));
            }else{
                categoryInfo.setNumber((byte) 0);
            }
            categoryInfoMapper.updateByPrimaryKeySelective(categoryInfo);
            articleCategoryMapper.delete(category);
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
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String modifiedDate = dateFormat.format(date);//更新时间ModifiedBy
        SnowFlake snowFlake = new SnowFlake(2, 3);
        long categoryId = snowFlake.nextId();

        articleForm.setModifiedBy(modifiedDate);
        String name = articleForm.getName();
        String[] categories = name.split(",");

        ArticleInfo articleInfo = new ArticleInfo();
        CategoryInfo categoryInfo = new CategoryInfo();
        ArticleContent articleContent = new ArticleContent();
        ArticleCategory articleCategory = new ArticleCategory();
        //更新ArticleInfo
        Long articleId = articleForm.getId();
        articleInfo.setId(articleId);
        articleInfo.setIsTop(false);
        articleInfo.setSummary(articleForm.getSummary());
        articleInfo.setTitle(articleForm.getTitle());
        articleInfo.setModifiedBy(modifiedDate);
        articleInfoMapper.updateByPrimaryKeySelective(articleInfo);
        //更新category&articleCategory
        articleCategory.setArticleId(articleId);
        List<ArticleCategory> articleCategoryList = articleCategoryMapper.select(articleCategory);
        List<CategoryInfo> categoryInfos = categoryInfoMapper.selectAll();
        for (ArticleCategory category : articleCategoryList) {
            CategoryInfo categorysql = categoryInfoMapper.selectByPrimaryKey(category.getCategoryId());
            for (String categoryName : categories) {
                //如果传入的类别和之前的不符，例如 Java（新属性） != Hadoop（原来属性）
                if (categoryName != categorysql.getName()){
                    for (CategoryInfo info : categoryInfos) {
                        //如果标签不存在,是新写的，就insert数据库,在原来的Number属性上-1，存在而且是
                        if(categoryName != info.getName()){
                            categoryInfo.setId(categoryId);
                            categoryInfo.setNumber((byte) 1);
                            categoryInfo.setModifiedBy(modifiedDate);
                            categoryInfoMapper.insert(categoryInfo);
                            int num = categorysql.getNumber() - 1;
                            categorysql.setNumber((byte) num);
                            categorysql.setModifiedBy(modifiedDate);
                            categoryInfoMapper.updateByPrimaryKeySelective(categorysql);
                        }
                    }
                }
            }
        }
        //更新content
        articleContent.setArticleId(articleId);
        ArticleContent articleContentsql = articleContentMapper.selectOne(articleContent);
        articleContentsql.setContent(articleForm.getContent());
        articleContentsql.setModifiedBy(modifiedDate);
        articleContentMapper.updateByPrimaryKeySelective(articleContentsql);

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
