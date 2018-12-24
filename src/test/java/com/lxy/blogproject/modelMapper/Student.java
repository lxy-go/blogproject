package com.lxy.blogproject.modelMapper;

public class Student {
    public int id;
    public String name;
    public String city;
    public int age;
    public Boolean isLike;

    public Student(int id, String name, String city, int age,Boolean isLike) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.age = age;
        this.isLike = isLike;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Boolean getLike() {
        return isLike;
    }

    public void setLike(Boolean like) {
        isLike = like;
    }
}
