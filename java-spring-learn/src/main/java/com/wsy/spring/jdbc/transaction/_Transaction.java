package com.wsy.spring.jdbc.transaction;

import com.wsy.spring.jdbc.bean.AJdbc;
import com.wsy.spring.jdbc.util.JDBCUtils;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * DDL操作一旦执行,都会自动提交
 *   >set autocommit=false取消对DDL失效
 * DML默认情况下,一旦提交,就会自动提交
 *   >set autocommit=false取消
 * 默认在关闭连接时,自动提交数据
 *
 * 1. 事务添加到javaEE三层结构里面的Service层
 * 2. Spring事务管理操作
 *   - 两种方式,声明式,编程式
 * 3. 声明式事务管理,底层使用aop
 *   - 基于注解方式
 *   - 基于xml配置文件方式
 * 4. Spring事务管理API
 *   - 提供一个接口,代表事务管理器,这个接口针对不同的框架提供不同的实现
 *     - 在配置文件中配置事务管理器
 *     - 开启事务注解
 *      (1)。 引入名称空间 tx
 *      (2)。 开启事务注解
 *     - 在Service类上面（或Service类里面方法上面）添加事务注解@Transactional
 *      (1) 类上面,所有方法都添加事务
 *      (2) 方法上面,为方法添加事务
 *     - @Transactional参数配置
 *       value()
 *       transactionManager()
 *       propagation() default Propagation.REQUIRED; 传播行为
 *       isolation() default Isolation.DEFAULT; 隔离级别
 *       timeout() default -1; //规定时间内提交,否则回滚
 *       readOnly() default false;// fasle可crud,true只能r
 *       rollbackFor() default {}; 出现哪些错误,回滚 (rollbackFor = Exception.class)
 *       rollbackForClassName() default {}
 *       noRollbackFor() default {}; 不会滚
 *       noRollbackForClassName() default {};
 */
public class _Transaction {
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
    public void testUpdateTx()  {
        String sql = "update a_jdbc set amount=amount-? where id = ? ";
        String sql1 = "update a_jdbc set amount=amount+? where id = ? ";
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            System.out.println(conn.getAutoCommit());
            //取消数据的自动提交功能
            conn.setAutoCommit(false);
            update(conn,sql,100,2);
            System.out.println(10/0);
            update(conn,sql1,100,3);
            System.out.println("转账成功");
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            JDBCUtils.closeResource(conn,null);
        }
    }
    //******************************************************************
    //通用的查询操作,用于返回数据表中的一条记录(考虑事务)
    public  <T> T getInstance(Connection conn,Class<T> clazz, String sql, Object ...args){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
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
    public void testTxSelect() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        System.out.println(conn.getTransactionIsolation());
        conn.setAutoCommit(false);
        String sql = "select id,amount from a_jdbc where id = ?";
        AJdbc aJdbc = getInstance(conn,AJdbc.class,sql,2);
        System.out.println(aJdbc);
    }
    public void testTxUpdate() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        conn.setAutoCommit(false);
        String sql = "update a_jdbc set amount=amount + ? where id = ?";
        update(conn,sql,500,2);
        //Thread.sleep(15000);
        System.out.println("修改成功");
    }
    public static void main(String[] args) throws Exception {
        _Transaction tx = new _Transaction();
        tx.testTxUpdate();
        tx.testTxSelect();
    }
}
