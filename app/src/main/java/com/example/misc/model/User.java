package com.example.misc.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2018/3/13.
 */

public class User {
    @Expose
    private String id;

    private String name;
    @Expose
    @SerializedName("userAge")
    private int age;

    private int isAdult = -1;

    public User() {
    }

    public User(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int isAdult() {
        return isAdult;
    }

    public void setAdult(int adult) {
        isAdult = adult;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", isAdult=" + isAdult +
                '}';
    }
}
