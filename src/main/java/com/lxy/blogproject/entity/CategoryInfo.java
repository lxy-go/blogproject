package com.lxy.blogproject.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tbl_category_info")
public class CategoryInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 该分类下的文章数量
     */
    private Byte number;

    /**
     * 分类创建时间
     */
    @Column(name = "create_by")
    private Date createBy;

    /**
     * 分类修改时间
     */
    @Column(name = "modified_by")
    private Date modifiedBy;

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
     * 获取分类名称
     *
     * @return name - 分类名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置分类名称
     *
     * @param name 分类名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取该分类下的文章数量
     *
     * @return number - 该分类下的文章数量
     */
    public Byte getNumber() {
        return number;
    }

    /**
     * 设置该分类下的文章数量
     *
     * @param number 该分类下的文章数量
     */
    public void setNumber(Byte number) {
        this.number = number;
    }

    /**
     * 获取分类创建时间
     *
     * @return create_by - 分类创建时间
     */
    public Date getCreateBy() {
        return createBy;
    }

    /**
     * 设置分类创建时间
     *
     * @param createBy 分类创建时间
     */
    public void setCreateBy(Date createBy) {
        this.createBy = createBy;
    }

    /**
     * 获取分类修改时间
     *
     * @return modified_by - 分类修改时间
     */
    public Date getModifiedBy() {
        return modifiedBy;
    }

    /**
     * 设置分类修改时间
     *
     * @param modifiedBy 分类修改时间
     */
    public void setModifiedBy(Date modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}