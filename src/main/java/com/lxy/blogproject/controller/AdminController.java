package com.lxy.blogproject.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxy.blogproject.base.ApiResponse;
import com.lxy.blogproject.dao.ArticleInfoMapper;
import com.lxy.blogproject.dto.ArticleDTO;
import com.lxy.blogproject.entity.ArticleInfo;
import com.lxy.blogproject.form.ArticleForm;
import com.lxy.blogproject.service.ArticleService;
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

    @GetMapping("/index")
    public String index(){
        return "admin/index";
    }

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

    @GetMapping("/article/{id}")
    @ResponseBody
    public ApiResponse getArticle(@PathVariable("id") Long id){

        ArticleInfo article = articleService.getArticleById(id);
        return ApiResponse.ofSuccess(article);
    }

    @PostMapping("/article/add")
    @ResponseBody
    public ApiResponse save(ArticleForm articleForm){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String format = dateFormat.format(date);
        articleForm.setModified_by(format);
        articleForm.setTraffic(0);
        articleForm.setTop(false);
        articleService.save(articleForm);
        return ApiResponse.ofSuccess("insert successs");
    }

    @DeleteMapping("/article/{id}")
    @ResponseBody
    public ApiResponse delete(@PathVariable("id") Long id){
        articleService.deleteArticleById(id);
        return ApiResponse.ofSuccess("Success");
    }

    @GetMapping("/category")
    public String category(){
        return "admin/category";
    }

    @GetMapping("/comment")
    public String comment(){
        return "admin/comment";
    }

}
