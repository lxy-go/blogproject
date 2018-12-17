package com.lxy.blogproject.mybatis;

import com.lxy.blogproject.ApplicationTests;
import com.lxy.blogproject.entity.SysLog;
import com.lxy.blogproject.entity.SysView;
import com.lxy.blogproject.service.SysLogService;
import com.lxy.blogproject.service.SysViewService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MyBatisTest extends ApplicationTests {

    @Autowired
    SysViewService sysViewService;

    @Autowired
    SysLogService sysLogService;

    @Test
    public void sys_view(){
        SysView sysView = sysViewService.selectByKey(1L);
        System.out.println(sysView);
    }
    @Test
    public void save(){
        SysLog sysLog = new SysLog();
        sysLog.setIp("192.168.179.101");
        sysLog.setOperateBy("a");
        sysLog.setOperateUrl("www.baidu.com");
        sysLog.setRemark("Remark");
        int save = sysLogService.save(sysLog);
        System.out.println(save);
    }
}
