package com.lxy.blogproject.modelMapper;

import com.lxy.blogproject.ApplicationTests;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import sun.nio.cs.ext.MacDingbat;

public class ModelMapperTest extends ApplicationTests {

    @Autowired
    ModelMapper modelMapper;
    @Test
    public void test1(){
        Student stu = new Student(1, "Lion", "Beijign", 14);
        People people = modelMapper.map(stu, People.class);
        System.out.println(people);
    }
}
