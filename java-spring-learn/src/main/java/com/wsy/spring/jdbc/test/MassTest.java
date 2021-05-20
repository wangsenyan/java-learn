package com.wsy.spring.jdbc.test;

import com.wsy.spring.jdbc.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.UUID;

/**
 * 批量操作
 * 1. Statement
 *    conn.createStatement()
 * 2. batch
 *    1. rewriteBatchedStatements=true
 *    2. addBatch() executeBatch() clearBatch()
 * 3. 连接问题
 *    1. 关闭自动提交
 *    2.
 */
public class MassTest {
    public void testInsert1(){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();
            String sql = "insert into ums_admin(username,password) values(?,?)";
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < 20000; i++) {
                ps.setObject(1,UUID.randomUUID().toString().replace("-",""));
                ps.setObject(2,UUID.randomUUID().toString().replace("-",""));
                //1.攒sql
                ps.execute();
            }
            long end = System.currentTimeMillis();
            System.out.println("花费时间: " + (end - start));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(conn,ps);
        }
    }
    public void testInsert2(){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();
            String sql = "insert into ums_admin(username,password) values(?,?)";
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < 20000; i++) {
                ps.setObject(1,UUID.randomUUID().toString().replace("-",""));
                ps.setObject(2,UUID.randomUUID().toString().replace("-",""));
                //1.攒sql
                ps.addBatch();
                if(i % 500 == 0) {
                    //2.执行batch
                    ps.executeBatch();
                    //3.清空batch
                    ps.clearBatch();
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("花费时间: " + (end - start));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(conn,ps);
        }
    }
    public void testInsert3(){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();
            String sql = "insert into ums_admin(username,password) values(?,?)";
            conn = JDBCUtils.getConnection();
            //设置不允许自动提交数据
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < 20000; i++) {
                ps.setObject(1,UUID.randomUUID().toString().replace("-",""));
                ps.setObject(2,UUID.randomUUID().toString().replace("-",""));
                //1.攒sql
                ps.addBatch();
                if(i % 500 == 0) {
                    //2.执行batch
                    ps.executeBatch();
                    //3.清空batch
                    ps.clearBatch();
                }
            }
            //提交数据
            conn.commit();
            long end = System.currentTimeMillis();
            System.out.println("花费时间: " + (end - start));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(conn,ps);
        }
    }
    public static void main(String[] args) {
        MassTest massTest = new MassTest();
        massTest.testInsert1();
    }
}
