package com.lxy.blogproject;

import com.lxy.blogproject.util.MyMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//启注解事务管理1
@EnableTransactionManagement  // 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
@MapperScan(basePackages = "com.lxy.blogproject.dao", markerInterface = MyMapper.class)
public class BlogprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogprojectApplication.class, args);
    }

}

