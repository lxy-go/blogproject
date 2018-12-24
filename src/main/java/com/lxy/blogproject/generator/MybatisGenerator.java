///*
//package com.lxy.blogproject.generator;
//
//import org.mybatis.generator.api.MyBatisGenerator;
//import org.mybatis.generator.config.Configuration;
//import org.mybatis.generator.config.xml.ConfigurationParser;
//import org.mybatis.generator.exception.XMLParserException;
//import org.mybatis.generator.internal.DefaultShellCallback;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.sql.SQLException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//
//public class MybatisGenerator {
//    public void generator() throws Exception{
//
//        List<String> warnings = new ArrayList<String>();
//        boolean overwrite = true;
//        //制定逆向工程的配置文件
//        File configFile = new File("E:\\code_0\\blogproject\\src\\main\\java\\com\\lxy\\blogproject\\generator\\generatorConfig.xml");
//        ConfigurationParser cp = new ConfigurationParser(warnings);
//        Configuration config = cp.parseConfiguration(configFile);
//        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
//        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
//                callback, warnings);
//        myBatisGenerator.generate(null);
//
//    }
//    public static void main(String[] args) throws Exception {
//        try {
//            MybatisGenerator generatorSqlmap = new MybatisGenerator();
//            generatorSqlmap.generator();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//}
//*/
