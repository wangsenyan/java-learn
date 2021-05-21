package com.wsy.spring.jdbc.datasource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;

public class C3P0 {
    public void testC3P0() throws Exception{
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass("com.mysql.jdbc.Driver");
        cpds.setJdbcUrl("jdbc:mysql://106.53.251.246:3306/mall");
        cpds.setUser("root");
        cpds.setPassword("-wsy12140322150232");
        //初始数据库连接池中的连接数
        cpds.setInitialPoolSize(10);
        cpds.setMaxPoolSize(100);
        Connection conn = cpds.getConnection();
        System.out.println(conn);
    }
    public void testC3P01() throws Exception {
        ComboPooledDataSource cpds = new ComboPooledDataSource("helloc3p0");
        Connection connection = cpds.getConnection();
        System.out.println(connection);
    }
    public static void main(String[] args) throws Exception {
        C3P0 c3p0 = new C3P0();
        c3p0.testC3P01();
    }
}
