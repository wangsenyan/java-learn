package com.wsy.spring.jdbc.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *@Description 操作数据库的工具类
 */
public class JDBCUtils {
    private static ComboPooledDataSource cpds = new ComboPooledDataSource("helloc3p0");
    private static DataSource dataSource;
    private static DataSource dataSource1;
    static {
        try {
            Properties pros = new Properties();
            //1.使用类加载器
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
            pros.load(is);
            dataSource1 = DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static {
        try {
            Properties pros = new Properties();
            //1.使用类加载器
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("dbcp.properties");
            pros.load(is);
            dataSource = BasicDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws Exception {
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("mysql.properties");
        Properties properties = new Properties();
        properties.load(is);
        String user = properties.getProperty("jdbc.mysql.user");
        String password = properties.getProperty("jdbc.mysql.password");
        String url = properties.getProperty("jdbc.mysql.url");
        String driverClass = properties.getProperty("jdbc.mysql.driverClass");

        Class.forName(driverClass);
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }
    public static Connection getPoolConnection() throws Exception {
        Connection conn = cpds.getConnection();
        return conn;
    }
    public static Connection getDPCPConnection() throws Exception {
        Connection conn = dataSource.getConnection();
        return conn;
    }
    public static Connection getDruidConnection() throws Exception {
        Connection conn = dataSource1.getConnection();
        return conn;
    }
    /**
     * 关闭资源的操作
     * @param conn
     * @param ps
     */
    public static void closeResource(Connection conn, Statement ps){
        //6. 资源关闭
        try {
            if(ps!=null)
                ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if(conn!=null)
                conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void closeResource(Connection conn, Statement ps, ResultSet rs){
        //6. 资源关闭
        try {
            if(ps!=null)
                ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if(conn!=null)
                conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if(rs!=null)
                rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static <T> List<T> select(Class<T> clazz,String sql,Object ...args){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int cc = rsmd.getColumnCount();
            List<T> result = new ArrayList<>();
            while (rs.next()){
                T t = clazz.newInstance();
                for (int i = 0; i < cc; i++) {
                    Object value = rs.getObject(i + 1);
                    String key = rsmd.getColumnLabel(i + 1);
                    Field field = clazz.getDeclaredField(key);
                    if(field!=null) {
                        field.setAccessible(true);
                        field.set(t, value);
                    }
                }
                result.add(t);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            closeResource(conn,ps,rs);
        }
        return null;
    }
    public static <T> int update(Class<T> clazz,String sql,Object ...args){
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            closeResource(conn,ps);
        }
        return 0;
    }
}
