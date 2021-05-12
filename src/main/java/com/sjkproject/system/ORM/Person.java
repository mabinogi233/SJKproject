package com.sjkproject.system.ORM;

import org.springframework.stereotype.Component;

@Component
public class Person {
    int Yid;

    int Bid;

    String YName;

    int Age;

    String Sex;

    String IDCardNumber;

    String Post;

    public int getYid() {
        return Yid;
    }

    public int getAge() {
        return Age;
    }

    public int getBid() {
        return Bid;
    }

    public String getIDCardNumber() {
        return IDCardNumber;
    }

    public String getPost() {
        return Post;
    }

    public String getSex() {
        return Sex;
    }

    public String getYName() {
        return YName;
    }

    public void setYid(int yid) {
        Yid = yid;
    }

    public void setAge(int age) {
        Age = age;
    }

    public void setBid(int bid) {
        Bid = bid;
    }

    public void setIDCardNumber(String IDCardNumber) {
        this.IDCardNumber = IDCardNumber;
    }

    public void setPost(String post) {
        Post = post;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public void setYName(String YName) {
        this.YName = YName;
    }
}
