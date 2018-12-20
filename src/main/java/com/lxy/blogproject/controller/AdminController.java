package com.lxy.blogproject.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxy.blogproject.dto.ArticleDTO;
import com.lxy.blogproject.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController{

    @Autowired
    ArticleService articleService;

    @GetMapping("/index")
    public String index(){
        return "admin/index";
    }

    @GetMapping("dashboard")
    public String dashboard(){
        return "admin/dashboard";
    }

    @GetMapping("/article-list")
    public String article(@RequestParam(value="pn",defaultValue="1")Integer pn, Model model){
        PageHelper.startPage(pn,5);
        List<ArticleDTO> articleDTOList =  articleService.getAllArticleInfo();
        PageInfo pageInfo = new PageInfo(articleDTOList);
        model.addAttribute("articles",pageInfo);
        return "admin/article-list";
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
