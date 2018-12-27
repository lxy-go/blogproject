package com.lxy.blogproject.service;

import com.lxy.blogproject.dto.ArticleCommentDTO;

public interface CommentService {
    void addArticleComment(ArticleCommentDTO articleCommentDto);
}
