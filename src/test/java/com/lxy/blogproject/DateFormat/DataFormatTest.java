package com.lxy.blogproject.DateFormat;

import com.lxy.blogproject.ApplicationTests;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataFormatTest extends ApplicationTests {
    @Test
    public void DateTest(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String format = dateFormat.format(date);
        System.out.println(format);
    }
}
