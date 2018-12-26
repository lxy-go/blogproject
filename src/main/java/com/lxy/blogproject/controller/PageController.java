package com.lxy.blogproject.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxy.blogproject.dao.ArticleInfoMapper;
import com.lxy.blogproject.dto.ArticleDTO;
import com.lxy.blogproject.entity.ArticleInfo;
import com.lxy.blogproject.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        PageHelper.startPage(pn,2);
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
        return "index";
    }

}
