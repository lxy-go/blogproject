package com.lxy.blogproject.dao;

import com.lxy.blogproject.entity.SysLog;
import com.lxy.blogproject.entity.SysLogExample;
import java.util.List;

public interface SysLogMapper {
    int insert(SysLog record);

    int insertSelective(SysLog record);

    List<SysLog> selectByExample(SysLogExample example);
}