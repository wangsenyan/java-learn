package com.wsy.spring.jdbc.dao;

import com.wsy.spring.jdbc.util.JDBCUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public abstract class BaseDao<T> {
    private Class<T> clazz;
    {
        //在继承关系中，不管父类还是子类，这些类里面的this都代表了最终new出来的那个类的实例对象，所以在父类中你可以用this获取到子类的信息！
        Type type = this.getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) type;
        Type[] typeArguments = paramType.getActualTypeArguments();
        clazz = (Class<T>) typeArguments[0];//泛型的第一个参数
    }
    public  T findOne(Connection conn, String sql, Object ...args){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int cc = rsmd.getColumnCount();
            if (rs.next()){
                T t = clazz.newInstance();
                for (int i = 0; i < cc; i++) {
                    Object value = rs.getObject(i + 1);
                    String key = rsmd.getColumnLabel(i + 1);
                    Field field = clazz.getDeclaredField(key);
                    field.setAccessible(true);
                    field.set(t, value);
                }
                return t;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(null,ps,rs);
        }
        return null;
    }
    public List<T> findAll(Connection conn, String sql, Object ...args){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
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
            JDBCUtils.closeResource(null,ps,rs);
        }
        return null;
    }
    //查询特殊值方法
    public <E> E getValue(Connection conn,String sql,Object ...args){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            if(rs.next()){
              return (E) rs.getObject(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(null,ps,rs);
        }
        return null;
    }
    public int update(Connection conn,String sql,Object ...args){
        //Connection conn = null;
        PreparedStatement ps = null;
        try {
            //conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null,ps);
        }
        return 0;
    }
}
