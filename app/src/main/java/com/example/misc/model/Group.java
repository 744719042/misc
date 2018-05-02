package com.example.misc.model;

import com.google.gson.annotations.Expose;

import java.util.Set;

/**
 * Created by Administrator on 2018/3/13.
 */

public class Group {
    @Expose
    private String id;
    @Expose
    private String name;
    @Expose
    private Set<User> userList;

    private int count;

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

    public Set<User> getUserList() {
        return userList;
    }

    public void setUserList(Set<User> userList) {
        this.userList = userList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", userList=" + userList +
                ", count=" + count +
                '}';
    }
}
