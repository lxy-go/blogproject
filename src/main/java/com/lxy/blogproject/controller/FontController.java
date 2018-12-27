package com.lxy.blogproject.controller;

import com.lxy.blogproject.base.ApiResponse;
import com.lxy.blogproject.dto.ArticleCommentDTO;
import com.lxy.blogproject.dto.ArticleDTO;
import com.lxy.blogproject.entity.Comment;
import com.lxy.blogproject.service.ArticleService;
import com.lxy.blogproject.service.CommentService;
import com.lxy.blogproject.util.MarkDown2HtmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FontController {
    @Autowired
    public ArticleService articleService;
    @Autowired
    CommentService commentService;

    @GetMapping("/article/list/lastest")
    public ApiResponse getLastestArticle(){
        List<ArticleDTO> lastArticle = articleService.getLastArticle();
        return ApiResponse.ofSuccess(lastArticle);
    }
    @GetMapping("/article/{id}")
    public ApiResponse getArticle(@PathVariable("id")Long id ){
        ArticleDTO article = articleService.getArticleById(id);
        return ApiResponse.ofSuccess(article);
    }

    /**
     * 获取文章的评论列表
     * @param id
     * @return
     */
    @GetMapping("/comment/article/{id}")
    public ApiResponse getComment(@PathVariable("id")Long id ){
        List<ArticleCommentDTO> articleCommentDTOList = articleService.getComment(id);
        return ApiResponse.ofSuccess(articleCommentDTOList);
    }

    @PostMapping("/comment/article/{id}")
    public ApiResponse addArticleComment(@PathVariable("id")Long id,@RequestBody ArticleCommentDTO articleCommentDto, HttpServletRequest request ){
        String ip = request.getRemoteAddr();
        articleCommentDto.setIp(ip);
        articleCommentDto.setArticleId(id);
        commentService.addArticleComment(articleCommentDto);
        return ApiResponse.ofSuccess("Success");
    }


}
