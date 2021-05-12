package com.wsy.spring.jdbc.connection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * 配置形式
 * 1. 配置和代码的分离
 * 2. 修改配置，避免重新打包
 */
public class Connection5 {
    public void testConnection5() throws Exception {
        InputStream is = Connection5.class.getClassLoader().getResourceAsStream("mysql.properties");
        Properties properties = new Properties();
        properties.load(is);
        String user = properties.getProperty("jdbc.mysql.user");
        String password = properties.getProperty("jdbc.mysql.password");
        String url = properties.getProperty("jdbc.mysql.url");
        String driverClass = properties.getProperty("jdbc.mysql.driverClass");

        Class.forName(driverClass);
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }

    public static void main(String[] args) throws Exception {
        new Connection5().testConnection5();
    }
}
