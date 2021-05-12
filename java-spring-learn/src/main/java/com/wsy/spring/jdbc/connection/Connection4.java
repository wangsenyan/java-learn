package com.wsy.spring.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * 自动加载驱动
 */
public class Connection4 {
    public void testConnection4()  throws Exception {
        //1. 提供另外三个连接的基本信息
        String uri = "jdbc:mysql://106.53.251.246:3306/mall";
        Properties info = new Properties();
        info.setProperty("user","root");
        info.setProperty("password","-wsy12140322150232");

        //Driver静态代码块自动加载
        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection(uri, info);
        System.out.println(connection);
    }

    public static void main(String[] args) throws Exception {
        new Connection4().testConnection4();
    }
}
