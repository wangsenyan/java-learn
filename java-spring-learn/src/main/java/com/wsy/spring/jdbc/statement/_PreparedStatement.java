package com.wsy.spring.jdbc.statement;

import com.wsy.spring.jdbc.bean.UmsAdmin;
import com.wsy.spring.jdbc.util.JDBCUtils;

import java.lang.reflect.Field;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * boolean BIT
 * byte TINYINT
 * short SMALLINT
 * int INTEGER
 * long BIGINT
 * String CHAR,VARCHAR,LONGVARCHAR
 * byte array BINARY,VAR BINARY
 * java.sql.Date DATE
 * java.sql.Time TIME
 * java.sql.Timestamp TIMESTAMP
 *
 * 必须声明SQL时,使用类的属性名来命名字段的别名
 * getColumnName->getColumnLabel
 */
public class _PreparedStatement {
    public void insertTest(){
        //Connection conn = null;
        ///PreparedStatement ps = null;
        try {
            //1. 获取连接
            //conn = JDBCUtils.getConnection();
            //2. sql
            String sql = "insert into ums_admin(username,password,create_time) values(?,?,?)";

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = sdf.parse("2021-5-14");

            update(sql,"凌志玲玲","fuck her",new Date(date.getTime()));
            //5. 执行操作
            //ps.execute();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //6. 资源关闭
            //DBCUtils.closeResource(conn,ps);
        }
    }

    public void updateTest(){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //1.连接
            conn = JDBCUtils.getConnection();
            //2.sql预编译
            String sql = "update ums_admin set username = ? where id = ?";
            ps = conn.prepareStatement(sql);
            //3.填充占位符
            ps.setObject(1,"莫扎特");
            ps.setObject(2,9);
            //4.执行
            /**
             * ps.execute();
             * 1. 查询操作，并有返回,true
             * 2. 增删改操作,没有返回结果,false
             */
            int i = ps.executeUpdate();
            //ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(conn,ps);
        }
    }
    //增删改
    public void update(String sql,Object ...args){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
          JDBCUtils.closeResource(conn,ps);
        }
    }
    public void deleteTest(){
        String sql = "delete from ums_admin_login_log where id= ? ";
        update(sql,1);
    }
    public void selectTest() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select * from ums_admin where id = ? ";

            ps = conn.prepareStatement(sql);
            ps.setObject(1,1);
            rs = ps.executeQuery();
            //处理结果集
            //判断下一个是否有数据,有返回true,并指向下一个
            if(rs.next()){
                UmsAdmin umsAdmin = new UmsAdmin();
                Long id = rs.getLong(1);
                umsAdmin.setId(id);
                String username = rs.getString(2);
                umsAdmin.setUsername(username);
                String password = rs.getString(3);
                umsAdmin.setPassword(password);
                String icon = rs.getString(4);
                umsAdmin.setIcon(icon);
                String email = rs.getString(5);
                umsAdmin.setEmail(email);
                String nick_name = rs.getString(6);
                umsAdmin.setNick_name(nick_name);
                String note = rs.getString(7);
                umsAdmin.setNote(note);
                Date create_time = rs.getDate(8);
                umsAdmin.setCreate_time(create_time);
                Date login_time = rs.getDate(9);
                umsAdmin.setLogin_time(login_time);
                int status = rs.getInt(10);
                umsAdmin.setStatus(status);
                System.out.println(umsAdmin);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(conn,ps,rs);
        }
    }
    public List<UmsAdmin> select(String sql, Object ...args) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            rs = ps.executeQuery();
            //获取结果集的元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            //获取结果集列数
            int columnCount = rsmd.getColumnCount();
            List<UmsAdmin> result = new ArrayList<>();
            while (rs.next()){
                UmsAdmin ua = new UmsAdmin();
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(i + 1);
                    //获取指定位置的列名(不是别名)->不推荐使用
                    String columnName = rsmd.getColumnName(i + 1);
                    //获取列的别名
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    //根据字段名获取字段并设置(反射)
                    Field field = UmsAdmin.class.getDeclaredField(columnLabel);
                    if(field!=null) {
                        field.setAccessible(true);
                        field.set(ua, columnValue);
                    }
                }
                result.add(ua);
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(conn,ps,rs);
        }
        return null;
    }
    public void selectTest1()  {
        String sql = "select * from ums_admin where status=?";
        List<UmsAdmin> select = null;
        try {
            select = select(sql, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(select);
    }

    /**
     * 通用查询
     * @param args
     */
    public <T> List<T> selectTest2(Class<T> clazz, String sql,Object ...args){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            rs = ps.executeQuery();
            //获取结果集的元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            //获取结果集列数
            int columnCount = rsmd.getColumnCount();
            List<T> result = new ArrayList<>();
            while (rs.next()){
                T t = clazz.newInstance();
                //UmsAdmin ua = new UmsAdmin();
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(i + 1);
                    //获取指定位置的列名(不是别名)->不推荐使用
                    String columnName = rsmd.getColumnName(i + 1);
                    //获取列的别名
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    //根据字段名获取字段并设置(反射)
                    Field field = clazz.getDeclaredField(columnLabel);
                    //Field field = UmsAdmin.class.getDeclaredField(columnLabel);
                    if(field!=null) {
                        field.setAccessible(true);
                        field.set(t, columnValue);
                    }
                }
                result.add(t);
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(conn,ps,rs);
        }
        return null;
    }
    public void select1(){
        String sql = "select * from ums_admin_login_log where id= ? ";
        String sql1= "select * from ums_admin where status=?";
        List<UmsAdmin> result = JDBCUtils.select(UmsAdmin.class, sql1, 1);
        //List<UmsAdminLog> result = selectTest2(UmsAdminLog.class,sql,2);
        System.out.println(result);
    }
    public static void main(String[] args) {
        _PreparedStatement _ps = new _PreparedStatement();
        _ps.select1();
    }
}
