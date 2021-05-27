package com.wsy.spring.jdbc.connection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 反射
 *    不出现第三方的api,使得程序具有更好的可移植性
 */
public class Connection2 {
    public void testConnection2() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        //1. 获取Driver实现类,使用反射
        Class<?> clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();
        //2.
        String uri = "jdbc:mysql://106.53.251.246:3306/mall";
        Properties info = new Properties();
        info.setProperty("user","root");
        info.setProperty("password","-wsy12140322150232");
        //4.获取连接
        Connection connect = driver.connect(uri, info);
        System.out.println(connect);
    }

    public static void main(String[] args) {
        try {
            new Connection2().testConnection2();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
