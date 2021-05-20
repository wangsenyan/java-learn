package com.wsy.spring.jdbc.dao;

import com.wsy.spring.jdbc.bean.UmsAdmin;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;

public interface UmsAdminDao {
    /**
     * @Description 插入一条记录
     * @param conn
     * @param admin
     */
    void create(Connection conn, UmsAdmin admin);

    /**
     * @Description 删除一条记录
     * @param conn
     * @param id
     */
    void destroyById(Connection conn, int id);

    /**
     * @Description 更新一条记录
     * @param conn
     * @param admin
     */
    void update(Connection conn, UmsAdmin admin) throws  Exception;

    /**
     * @Description 根据id查询
     * @param conn
     * @param id
     * @return
     */
    UmsAdmin findById(Connection conn, int id);

    /**
     * @Description 查询所有记录
     * @param conn
     * @return
     */
    List<UmsAdmin> find(Connection conn);

    /**
     * @Description 查询条数
     * @param conn
     * @return
     */
    Long count(Connection conn);

    /**
     * @Description 查找最大的薪资
     * @param conn
     * @return
     */
    Timestamp findMaxAmount(Connection conn);
}
