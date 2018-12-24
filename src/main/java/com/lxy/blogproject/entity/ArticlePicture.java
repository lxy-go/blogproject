package com.lxy.blogproject.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tbl_article_picture")
public class ArticlePicture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 对应文章id
     */
    @Column(name = "article_id")
    private Long articleId;

    /**
     * 图片url
     */
    @Column(name = "picture_url")
    private String pictureUrl;

    /**
     * 创建时间
     */
    @Column(name = "create_by")
    private Date createBy;

    /**
     * 更新时间
     */
    @Column(name = "modified_by")
    private String modifiedBy;

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
     * 获取对应文章id
     *
     * @return article_id - 对应文章id
     */
    public Long getArticleId() {
        return articleId;
    }

    /**
     * 设置对应文章id
     *
     * @param articleId 对应文章id
     */
    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    /**
     * 获取图片url
     *
     * @return picture_url - 图片url
     */
    public String getPictureUrl() {
        return pictureUrl;
    }

    /**
     * 设置图片url
     *
     * @param pictureUrl 图片url
     */
    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl == null ? null : pictureUrl.trim();
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
     * 获取更新时间
     *
     * @return modified_by - 更新时间
     */
    public String getModifiedBy() {
        return modifiedBy;
    }

    /**
     * 设置更新时间
     *
     * @param modifiedBy 更新时间
     */
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy == null ? null : modifiedBy.trim();
    }
}