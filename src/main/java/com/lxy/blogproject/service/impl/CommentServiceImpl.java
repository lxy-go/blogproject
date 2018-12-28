package com.lxy.blogproject.service.impl;

import com.lxy.blogproject.dao.ArticleCommentMapper;
import com.lxy.blogproject.dao.CommentMapper;
import com.lxy.blogproject.dto.ArticleCommentDTO;
import com.lxy.blogproject.entity.ArticleComment;
import com.lxy.blogproject.entity.Comment;
import com.lxy.blogproject.service.CommentService;
import com.lxy.blogproject.util.SnowFlake;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ArticleCommentMapper  articleCommentMapper;
    @Autowired
    CommentMapper commentMapper;

    /**
     * 添加一条评论
     * @param articleCommentDto
     */
    @Override
    public void addArticleComment(ArticleCommentDTO articleCommentDto) {
        SnowFlake snowFlake = new SnowFlake(2, 3);
        long articleCommentId = snowFlake.nextId();
        long commentId = snowFlake.nextId();

        Long articleId = articleCommentDto.getArticleId();
        ArticleComment articleComment = new ArticleComment();
        articleComment.setArticleId(articleId);
        articleComment.setCommentId(commentId);
        articleComment.setIsEffective(true);
        articleComment.setId(articleCommentId);
        articleCommentMapper.insert(articleComment);

        Comment comment = modelMapper.map(articleCommentDto, Comment.class);
        comment.setId(commentId);
        comment.setIsEffective(true);
        commentMapper.insert(comment);
    }
}
