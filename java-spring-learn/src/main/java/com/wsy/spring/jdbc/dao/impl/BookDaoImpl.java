package com.wsy.spring.jdbc.dao.impl;

import com.wsy.spring.aop.aspect.Book;
import com.wsy.spring.jdbc.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void update(Book book){
        String sql = "insert into book value(?,?)";
        jdbcTemplate.update(sql,book.getId(),book.getName());
    }
}
