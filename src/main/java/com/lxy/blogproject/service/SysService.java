package com.lxy.blogproject.service;

import com.lxy.blogproject.entity.SysLog;
import com.lxy.blogproject.entity.SysView;

import java.util.List;

public interface SysService  {

    void addLog(SysLog sysLog);

    void addView(SysView sysView);

    int getLogCount();

    int getViewCount();

    public List<SysView> listAllView();

    public List<SysLog> listAllLogs();

}
