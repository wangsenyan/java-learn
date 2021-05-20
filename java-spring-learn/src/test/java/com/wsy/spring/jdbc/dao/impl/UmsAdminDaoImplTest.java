package com.wsy.spring.jdbc.dao.impl;

import com.wsy.spring.jdbc.bean.UmsAdmin;
import com.wsy.spring.jdbc.util.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

class UmsAdminDaoImplTest {
    UmsAdminDaoImpl dao ;
    UmsAdmin umsAdmin;
    UmsAdminDaoImplTest(){
        dao = new UmsAdminDaoImpl();
        umsAdmin = new UmsAdmin();
        umsAdmin.setId((long)2);
        umsAdmin.setUsername("西村妮娜");
        umsAdmin.setPassword(UUID.randomUUID().toString().replace("-",""));
        umsAdmin.setIcon("http://www.pornhub.com");
        umsAdmin.setEmail("xicun@sina.com");
        umsAdmin.setNick_name("nina");
        umsAdmin.setNote("av actor");
        umsAdmin.setCreate_time(new Date());
        umsAdmin.setLogin_time(new Date());
        umsAdmin.setStatus(1);
    }
    @Test
    void create() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        dao.create(conn,umsAdmin);
    }

    @Test
    void destroyById() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        dao.destroyById(conn,24620);
    }

    @Test
    void update() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            UmsAdmin ua = new UmsAdmin();
            ua.setId(64621L);
            ua.setIcon("nimade");
            dao.update(conn,ua);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(conn,null);
        }
    }

    @Test
    void findById() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select * from ums_admin where id =?";
            UmsAdmin one = dao.findOne(conn,sql, 24621);
            System.out.println(one);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(conn,null);
        }

    }

    @Test
    void find() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select * from ums_admin ";
            List<UmsAdmin> all = dao.findAll(conn, sql);
            System.out.println(all);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(conn,null);
        }

    }

    @Test
    void count() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select count(*) from ums_admin";
            Long count =  dao.getValue(conn,sql);
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(conn,null);
        }
    }

    @Test
    void findMaxAmount() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            Timestamp maxAmount = dao.findMaxAmount(conn);
            System.out.println(maxAmount);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(conn,null);
        }
    }
}