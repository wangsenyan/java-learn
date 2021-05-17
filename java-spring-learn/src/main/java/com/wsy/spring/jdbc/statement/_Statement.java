package com.wsy.spring.jdbc.statement;

import com.wsy.spring.jdbc.bean.User;
import com.wsy.spring.jdbc.util.JDBCUtils;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/**
 * Statement: 执行静态SQL语句并返回它所生成结果的对象
 *
 * PreparedStatement: SQL语句被预编译并存储在此对象中,可以使用此对象多次高效地执行该语句
 * 1. sql注入
 * 2. blob数据
 * 3. 更高效批量插入
 * CallableStatement: 执行SQL存储过程
 */
public class _Statement {
    public void testLogin(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名: ");
        String username = scanner.nextLine();
        System.out.println("请输入密码: ");
        String password = scanner.nextLine();
        String sql = "select username,password from ums_admin where username = '"+username+"' and password = '"+password+"'";
        String sql1 = "select username,password from ums_admin where username = ? and password = ? ";
        System.out.println(sql1);
        List<User> r = JDBCUtils.select(User.class, sql1, username, password);
        //User user = get(sql, User.class);
//        if(user!=null){
//            System.out.println("登录成功");
//        }else{
//            System.out.println("登录失败");
//        }
        if(r.size()>0){
            System.out.println("登录成功");
        }else {
            System.out.println("登录失败");
        }
    }
    public <T> T get(String sql,Class<T> clazz){
        T t = null;
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            Connection connection = getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            if(rs.next()){
                t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    String columnName = rsmd.getColumnName(i + 1);
                    Object columnValue = rs.getObject(columnName);
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t,columnValue);
                }
                return t;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(rs!=null){
                try {
                    rs.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(st!=null){
                try {
                    st.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return null;
    }
    public Connection getConnection() throws  Exception {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("mysql.properties");
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

    public static void main(String[] args) {
        _Statement statement = new _Statement();
        statement.testLogin();
    }
}
