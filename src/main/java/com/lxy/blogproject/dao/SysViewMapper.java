package com.lxy.blogproject.dao;

import com.lxy.blogproject.entity.SysView;
import com.lxy.blogproject.entity.SysViewExample;
import java.util.List;

public interface SysViewMapper {
    int insert(SysView record);

    int insertSelective(SysView record);

    List<SysView> selectByExample(SysViewExample example);
}