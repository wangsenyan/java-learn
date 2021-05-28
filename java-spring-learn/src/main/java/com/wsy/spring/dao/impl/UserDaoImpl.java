package com.wsy.spring.dao.impl;

import com.wsy.spring.dao.UserDao;

public class UserDaoImpl implements UserDao {
    @Override
    public int add(int a, int b) {
        System.out.println("add()执行");
        return a + b;
    }

    @Override
    public String update(String id) {
        System.out.println("update()执行");
       return id;
    }
}
