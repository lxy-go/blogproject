package com.lxy.blogproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.Date;

public class ArticleDTO {
    // tbl_article_info基础字段
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;                // 主键
    private String title;           // 文章标题
    private String summary;         // 文章简介
    public Boolean isTop;          // 文章是否置顶
    private Integer traffic;        // 文章浏览量
    private Date createBy;          // 文章创建时间

    // tbl_article_content基础字段
    private String content;         // 文章内容

    // tbl_category_info基础字段
    private String categoryName;    // 分类名称

    private Byte categoryNumber;    // 分类对应的数量

    private String pictureUrl;      // 文章题图url

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getCreateBy() {
        return createBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Boolean getTop() {
        return isTop;
    }

    public void setTop(Boolean top) {
        isTop = top;
    }

    public Integer getTraffic() {
        return traffic;
    }

    public void setTraffic(Integer traffic) {
        this.traffic = traffic;
    }

    public void setCreateBy(Date createBy) {
        this.createBy = createBy;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Byte getCategoryNumber() {
        return categoryNumber;
    }

    public void setCategoryNumber(Byte categoryNumber) {
        this.categoryNumber = categoryNumber;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
