package com.wsy.spring.jdbc.dao.impl;

import com.wsy.spring.jdbc.bean.AJdbc;
import com.wsy.spring.jdbc.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public void add(AJdbc aJdbc) {
        String sql = "update a_jdbc set amount=amount-? where id=?";
        jdbcTemplate.update(sql,aJdbc.getAmount(),aJdbc.getId());
    }

    @Override
    public void reduce(AJdbc aJdbc) {
        String sql = "update a_jdbc set amount=amount+? where id=?";
        jdbcTemplate.update(sql,aJdbc.getAmount(),aJdbc.getId());
    }
}
