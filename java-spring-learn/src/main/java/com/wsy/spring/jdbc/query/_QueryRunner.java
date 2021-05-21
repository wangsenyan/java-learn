package com.wsy.spring.jdbc.query;

import com.wsy.spring.jdbc.bean.UmsAdmin;
import com.wsy.spring.jdbc.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class _QueryRunner {
    public void testInsert() throws Exception {
        QueryRunner runner = new QueryRunner();
        Connection conn = JDBCUtils.getDruidConnection();
        String sql = "insert into a_jdbc(amount) values(?)";
        int count = runner.update(conn, sql, 1200.00);
        JDBCUtils.closeResource(conn,null);
        System.out.println("添加了 " + count + " 数据");
    }

    /**
     * BeanHandler 单条结果
     * BeanListHandler 一条结果
     * @throws Exception
     */
    public void testQuery() {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getDPCPConnection();
            String sql = "select * from ums_admin where id >= ?";
            BeanListHandler<UmsAdmin> handler = new BeanListHandler<>(UmsAdmin.class);
            MapListHandler handler1 = new MapListHandler();
            List<UmsAdmin> result = runner.query(conn, sql, handler, 24623);
            List<Map<String, Object>> query = runner.query(conn, sql, handler1, 24623);
            System.out.println(query);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(conn,null);
        }
    }

    public void testQuery1() {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getDPCPConnection();
            String sql = "select count(*) from ums_admin";
            ScalarHandler handler = new ScalarHandler();
            Long count = (Long) runner.query(conn, sql, handler);
            System.out.println(count);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(conn,null);
        }
    }

    /**
     * 自定义ResultSetHandler
     */
    public void testQuery2() {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getDPCPConnection();

            String sql = "select * from ums_admin where id = ?";

            ResultSetHandler<UmsAdmin> handler = new ResultSetHandler<UmsAdmin>() {
                @Override
                public UmsAdmin handle(ResultSet rs) throws SQLException {
                    UmsAdmin umsAdmin = new UmsAdmin();
                    ResultSetMetaData metaData = rs.getMetaData();
                    int columnCount = metaData.getColumnCount();
                    if(rs.next()){
                        for (int i = 0; i < columnCount; i++) {
                            Object object = rs.getObject(i + 1);
                            String columnLabel = metaData.getColumnLabel(i + 1);
                            try {
                                Field field = UmsAdmin.class.getDeclaredField(columnLabel);
                                field.setAccessible(true);
                                field.set(umsAdmin,object);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    return umsAdmin;
                }
            };
            UmsAdmin query = runner.query(conn, sql, handler,24623);
            System.out.println(query);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(conn,null);
        }
    }
    public static void main(String[] args) throws Exception {
        _QueryRunner queryRunner = new _QueryRunner();
        queryRunner.testQuery2();
    }
}
