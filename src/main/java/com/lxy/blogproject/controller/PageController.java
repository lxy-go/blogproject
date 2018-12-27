package com.lxy.blogproject.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxy.blogproject.base.ApiResponse;
import com.lxy.blogproject.dao.ArticleInfoMapper;
import com.lxy.blogproject.dto.ArticleDTO;
import com.lxy.blogproject.entity.ArticleInfo;
import com.lxy.blogproject.service.ArticleService;
import com.lxy.blogproject.util.MarkDown2HtmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller

public class PageController {

    @Autowired
    ArticleInfoMapper articleInfoMapper;
    @Autowired
    ArticleService articleService;
    /**
     * 导航起始页
     * @return
     */
    @GetMapping("/admin/index")
    public String adminIndex(){
        return "admin/index";
    }

    /**
     * 导航统计面板
     * @return
     */
    @GetMapping("/admin/dashboard")
    public String dashboard(){
        return "admin/article-dashboard";
    }

    /**
     * 返回文章列表
     * @param pn
     * @param model
     * @return
     */
    @GetMapping("/admin/article-list")
    public String article(@RequestParam(value="pn",defaultValue="1")Integer pn, Model model){
        PageHelper.startPage(pn,5);
        List<ArticleInfo> articleInfos = articleInfoMapper.selectAll();
        PageInfo pageInfo = new PageInfo(articleInfos,5);
        model.addAttribute("articles",pageInfo);
        return "admin/article-list";
    }

    @GetMapping("/admin/article-comment")
    public String comment(){
        return "admin/article-comment";
    }

    @GetMapping("/")
    public String index(Model model){
        List<ArticleDTO> articles = articleService.getLastArticle();
        model.addAttribute("lastArticles",articles);
        return "index";
    }
    @GetMapping("/article/{articleId}")
    public String getArticlePage(@PathVariable("articleId") Long articleId ,Model model){
        ArticleDTO article = articleService.getArticleById(articleId);
        article.setContent(MarkDown2HtmlUtil.markdown2html(article.getContent()));
        model.addAttribute("article",article);
        return "article";
    }


}
