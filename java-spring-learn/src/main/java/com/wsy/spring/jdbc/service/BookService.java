package com.wsy.spring.jdbc.service;

import com.wsy.spring.jdbc.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BookDao bookDao;
}
