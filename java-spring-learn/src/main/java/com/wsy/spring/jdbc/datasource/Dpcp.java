package com.wsy.spring.jdbc.datasource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * jar:
 *  - commons-dbcp-1.4.jar
 *  - commons-pool-1.5.5.jar
 */
public class Dpcp {
    public void testGetConnection() throws SQLException {
        BasicDataSource source = new BasicDataSource();
        source.setDriverClassName("com.mysql.jdbc.Driver");
        source.setUrl("jdbc:mysql://106.53.251.246:3306/mall?characterEncoding=UTF-8&rewriteBatchedStatements=true");
        source.setUsername("root");
        source.setPassword("-wsy12140322150232");
        source.setInitialSize(10);
        source.setMaxActive(10);

        Connection conn = source.getConnection();
        System.out.println(conn);
    }
    //使用配置文件
    public void testGetConnection1() throws Exception {
        Properties pros = new Properties();

        //1.使用类加载器
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("dbcp.properties");
        pros.load(is);
        DataSource dataSource = BasicDataSourceFactory.createDataSource(pros);
        Connection connection = dataSource.getConnection();
        System.out.println(connection);

    }
    public static void main(String[] args) throws Exception {
        Dpcp dpcp = new Dpcp();
        dpcp.testGetConnection1();
    }
}
