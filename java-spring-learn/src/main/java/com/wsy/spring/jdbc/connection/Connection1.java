package com.wsy.spring.jdbc.connection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Driver
 * 0. Driver
 * 1. url
 * 2. properties
 *    - user
 *    - password
 */
public class Connection1 {
    String url;
    Properties info;
    public Connection1(String url, Properties info) {
        this.url = url;
        this.info = info;
    }
    public  void testConnection1() throws SQLException {
        //Driver driver = new com.mysql.jdbc.Driver();
        Driver driver = null;
        Connection connect = driver.connect(url, info);
        System.out.println(connect);
    }

    public static void main(String[] args) throws SQLException {
        String uri = "jdbc:mysql://106.53.251.246:3306/mall";
        Properties info = new Properties();
        info.setProperty("user","root");
        info.setProperty("password","-wsy12140322150232");
        Connection1 con = new Connection1(uri, info);
        con.testConnection1();
    }
}
