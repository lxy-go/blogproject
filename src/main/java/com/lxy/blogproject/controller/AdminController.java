package com.lxy.blogproject.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxy.blogproject.base.ApiResponse;
import com.lxy.blogproject.dao.ArticleInfoMapper;
import com.lxy.blogproject.entity.ArticleCategory;
import com.lxy.blogproject.entity.ArticleContent;
import com.lxy.blogproject.entity.ArticleInfo;
import com.lxy.blogproject.entity.CategoryInfo;
import com.lxy.blogproject.form.ArticleForm;
import com.lxy.blogproject.service.ArticleService;
import com.lxy.blogproject.util.SnowFlake;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController{

    @Autowired
    ArticleService articleService;
    @Autowired
    ArticleInfoMapper articleInfoMapper;
    @Autowired
    ModelMapper modelMapper;

    /**
     * 导航起始页
     * @return
     */
    @GetMapping("/index")
    public String index(){
        return "admin/index";
    }

    /**
     * 导航统计面板
     * @return
     */
    @GetMapping("dashboard")
    public String dashboard(){
        return "admin/dashboard";
    }

    /**
     * 返回文章列表
     * @param pn
     * @param model
     * @return
     */
    @GetMapping("/article-list")
    public String article(@RequestParam(value="pn",defaultValue="1")Integer pn, Model model){
        PageHelper.startPage(pn,5);
        List<ArticleInfo> articleInfos = articleInfoMapper.selectAll();
        PageInfo pageInfo = new PageInfo(articleInfos,5);
        model.addAttribute("articles",pageInfo);
        return "admin/article-list";
    }

    @GetMapping("/articles")
    @ResponseBody
    public PageInfo getAllArticles(){
        PageHelper.startPage(1,5);
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

    /**
     * 查询用户评论
     * @return
     */
    @GetMapping("/comment")
    public String comment(){
        return "admin/comment";
    }

    @PostMapping("/article/update/{id}")
    @ResponseBody
    public ApiResponse updateArticle(@PathVariable("id") Long id,ArticleForm articleForm){
        articleForm.setId(id);
        articleService.update(articleForm);
        return ApiResponse.ofSuccess("Success");
    }

}
