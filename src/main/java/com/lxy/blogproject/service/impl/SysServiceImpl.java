package com.lxy.blogproject.service.impl;

import com.lxy.blogproject.dao.SysLogMapper;
import com.lxy.blogproject.dao.SysViewMapper;
import com.lxy.blogproject.entity.SysLog;
import com.lxy.blogproject.entity.SysView;
import com.lxy.blogproject.service.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysServiceImpl   implements SysService {
    @Autowired
    SysLogMapper sysLogMapper;

    @Autowired
    SysViewMapper sysViewMapper;

    //选择性添加一条日志
    @Override
    public void addLog(SysLog sysLog) {
        sysLogMapper.insertSelective(sysLog);
    }

    @Override
    public void addView(SysView sysView) {
        sysViewMapper.insertSelective(sysView);
    }

    @Override
    public int getLogCount() {
        int count = sysLogMapper.selectAll().size();
        return count;
    }

    @Override
    public int getViewCount() {
        int count = sysViewMapper.selectAll().size();
        return count;
    }

    @Override
    public List<SysView> listAllView() {
        List<SysView> sysViews = sysViewMapper.selectAll();
        return sysViews;
    }

    @Override
    public List<SysLog> listAllLogs() {
        List<SysLog> sysLogs = sysLogMapper.selectAll();
        return sysLogs;
    }
}
