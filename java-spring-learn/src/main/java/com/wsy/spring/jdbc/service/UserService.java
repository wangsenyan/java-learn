package com.wsy.spring.jdbc.service;

import com.wsy.spring.jdbc.bean.AJdbc;
import com.wsy.spring.jdbc.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Transactional
    public void transfer(){
        AJdbc aJdbc = new AJdbc();
        aJdbc.setId(BigInteger.valueOf(2));
        aJdbc.setAmount(BigDecimal.valueOf(100));
        userDao.add(aJdbc);
        AJdbc bJdbc = new AJdbc();
        bJdbc.setId(BigInteger.valueOf(3));
        bJdbc.setAmount(BigDecimal.valueOf(100));
        userDao.reduce(bJdbc);
    }
}
