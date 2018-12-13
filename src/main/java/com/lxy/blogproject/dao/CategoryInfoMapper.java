package com.lxy.blogproject.dao;

import com.lxy.blogproject.entity.CategoryInfo;
import com.lxy.blogproject.entity.CategoryInfoExample;
import java.util.List;

public interface CategoryInfoMapper {
    int insert(CategoryInfo record);

    int insertSelective(CategoryInfo record);

    List<CategoryInfo> selectByExample(CategoryInfoExample example);
}