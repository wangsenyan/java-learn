package com.wsy.spring.jdbc.connection;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * DriverManger
 */
public class Connection3 {
    public void testConnection3() throws Exception {
        //1. 获取Driver实现类的对象
        Class<?> clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();
        //2. 提供另外三个连接的基本信息
        String uri = "jdbc:mysql://106.53.251.246:3306/mall";
        Properties info = new Properties();
        info.setProperty("user","root");
        info.setProperty("password","-wsy12140322150232");
        //3.注册驱动
        DriverManager.registerDriver(driver);
        //4.获取连接
        Connection connection = DriverManager.getConnection(uri, info);
        System.out.println(connection);
    }

    public static void main(String[] args) throws Exception {
        new Connection3().testConnection3();
    }
}
