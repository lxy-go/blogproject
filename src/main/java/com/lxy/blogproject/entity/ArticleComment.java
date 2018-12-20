package com.lxy.blogproject.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tbl_article_comment")
public class ArticleComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 文章ID
     */
    @Column(name = "article_id")
    private Long articleId;

    /**
     * 对应的留言ID
     */
    @Column(name = "comment_id")
    private Long commentId;

    /**
     * 创建时间
     */
    @Column(name = "create_by")
    private Date createBy;

    /**
     * 是否有效，默认为1有效，置0无效
     */
    @Column(name = "is_effective")
    private Boolean isEffective;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取文章ID
     *
     * @return article_id - 文章ID
     */
    public Long getArticleId() {
        return articleId;
    }

    /**
     * 设置文章ID
     *
     * @param articleId 文章ID
     */
    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    /**
     * 获取对应的留言ID
     *
     * @return comment_id - 对应的留言ID
     */
    public Long getCommentId() {
        return commentId;
    }

    /**
     * 设置对应的留言ID
     *
     * @param commentId 对应的留言ID
     */
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    /**
     * 获取创建时间
     *
     * @return create_by - 创建时间
     */
    public Date getCreateBy() {
        return createBy;
    }

    /**
     * 设置创建时间
     *
     * @param createBy 创建时间
     */
    public void setCreateBy(Date createBy) {
        this.createBy = createBy;
    }

    /**
     * 获取是否有效，默认为1有效，置0无效
     *
     * @return is_effective - 是否有效，默认为1有效，置0无效
     */
    public Boolean getIsEffective() {
        return isEffective;
    }

    /**
     * 设置是否有效，默认为1有效，置0无效
     *
     * @param isEffective 是否有效，默认为1有效，置0无效
     */
    public void setIsEffective(Boolean isEffective) {
        this.isEffective = isEffective;
    }
}