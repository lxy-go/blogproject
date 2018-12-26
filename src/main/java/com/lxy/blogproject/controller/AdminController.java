package com.lxy.blogproject.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxy.blogproject.base.ApiResponse;
import com.lxy.blogproject.dao.ArticleInfoMapper;
import com.lxy.blogproject.entity.ArticleContent;
import com.lxy.blogproject.entity.ArticleInfo;
import com.lxy.blogproject.entity.ArticlePicture;
import com.lxy.blogproject.entity.CategoryInfo;
import com.lxy.blogproject.form.ArticleForm;
import com.lxy.blogproject.service.ArticleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    ArticleService articleService;
    @Autowired
    ArticleInfoMapper articleInfoMapper;
    @Autowired
    ModelMapper modelMapper;


    @GetMapping("/articles")
    @ResponseBody
    public PageInfo getAllArticles(){
        PageHelper.startPage(1,2);
        List<ArticleInfo> articleInfos = articleInfoMapper.selectAll();
        PageInfo pageInfo = new PageInfo(articleInfos,5);
        return pageInfo;
    }
    /**\
     * 根据ID返回文章ArticleInfo
     * @param id
     * @return
     */
    @GetMapping("/article/{id}")
    @ResponseBody
    public ApiResponse getArticle(@PathVariable("id") Long id){

        ArticleInfo article = articleService.getArticleInfoById(id);
        return ApiResponse.ofSuccess(article);
    }

    /**
     * 添加一篇文章
     * @param articleForm
     * @return
     */
    @PostMapping("/article/add")
    @ResponseBody
    public ApiResponse save(ArticleForm articleForm){
        articleService.save(articleForm);
        return ApiResponse.ofSuccess("insert successs");
    }

    /**
     * 删除一篇文章
     * @param id
     * @return
     */
    @DeleteMapping("/article/{id}")
    @ResponseBody
    public ApiResponse delete(@PathVariable("id") Long id){
        articleService.deleteArticleById(id);
        return ApiResponse.ofSuccess("Success");
    }

    /**
     * 根据ID查询标签
     * @param id
     * @return
     */
    @GetMapping("/category/{id}")
    @ResponseBody
    public ApiResponse getCategory(@PathVariable("id") Long id){
        List<CategoryInfo> categories = articleService.getCategorysByArticleId(id);
        return ApiResponse.ofSuccess(categories);
    }

    /**
     * 根据ID查询文章内容
     * @param id
     * @return
     */
    @GetMapping("/content/{id}")
    @ResponseBody
    public ApiResponse getContent(@PathVariable("id") Long id){
        ArticleContent content = articleService.getContentByArticleId(id);
        return ApiResponse.ofSuccess(content);
    }

    @GetMapping("/pictureUrl/{id}")
    @ResponseBody
    public ApiResponse getPicture(@PathVariable("id") Long id){
        ArticlePicture articlePicture = articleService.getPictureUrlByArtId(id);
        return ApiResponse.ofSuccess(articlePicture);
    }

    @PostMapping("/article/update/{id}")
    @ResponseBody
    public ApiResponse updateArticle(@PathVariable("id") Long id,ArticleForm articleForm){
        articleForm.setId(id);
        articleService.update(articleForm);
        return ApiResponse.ofSuccess("Success");
    }

}
