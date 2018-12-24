package com.lxy.blogproject.mybatis;

import com.lxy.blogproject.ApplicationTests;
import com.lxy.blogproject.dao.ArticleCategoryMapper;
import com.lxy.blogproject.dao.CategoryInfoMapper;
import com.lxy.blogproject.entity.ArticleCategory;
import com.lxy.blogproject.entity.CategoryInfo;
import com.lxy.blogproject.util.SnowFlake;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyBatisTest extends ApplicationTests {

    @Autowired
    CategoryInfoMapper categoryInfoMapper;
    @Autowired
    ArticleCategoryMapper articleCategoryMapper;

    @Test
    public void selectLike(){
        CategoryInfo categoryInfo = new CategoryInfo();
        categoryInfo.setName("cate");
        CategoryInfo categoryInfo1 = categoryInfoMapper.selectOne(categoryInfo);
        if (categoryInfo1!=null){
            System.out.println(categoryInfo1.getNumber());
        }else{
            System.out.println("没有");
        }
    }
    @Test
    public void categoryInsert(){
        CategoryInfo categoryInfo = new CategoryInfo();

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = dateFormat.format(date);//更新时间ModifiedBy
        SnowFlake snowFlake = new SnowFlake(2, 3);
        long snowId = snowFlake.nextId();
        long snowId2 = snowFlake.nextId();
        System.out.println("snow1 "+snowId+"  snow2 "+snowId2);
//        categoryInfo.setName("z");
//        categoryInfo.setModifiedBy(format);
//        categoryInfo.setId(snowId);
//        categoryInfo.setNumber((byte) 0);
//        categoryInfoMapper.insert(categoryInfo);
    }
    @Test
    public void updatetest(){
        CategoryInfo categoryInfo = new CategoryInfo();
        categoryInfo.setNumber((byte) 2);
        categoryInfo.setId(274540662475730945L);

        categoryInfoMapper.updateByPrimaryKeySelective(categoryInfo);
    }
    @Test
    public void deleteOne(){
        Long articleId = 274586036557066240L;
        CategoryInfo categoryInfo = new CategoryInfo();


    }

}
