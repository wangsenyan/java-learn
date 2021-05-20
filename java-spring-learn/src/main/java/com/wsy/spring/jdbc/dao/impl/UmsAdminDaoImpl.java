package com.wsy.spring.jdbc.dao.impl;

import com.wsy.spring.jdbc.bean.UmsAdmin;
import com.wsy.spring.jdbc.dao.BaseDao;
import com.wsy.spring.jdbc.dao.UmsAdminDao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UmsAdminDaoImpl extends BaseDao<UmsAdmin> implements UmsAdminDao {

    @Override
    public void create(Connection conn, UmsAdmin admin) {
        String sql = "insert into ums_admin(username,password,icon,email,nick_name,note,create_time,login_time,status) values(?,?,?,?,?,?,?,?,?)";
        update(conn,sql,
                admin.getUsername(),
                admin.getPassword(),
                admin.getIcon(),
                admin.getEmail(),
                admin.getNick_name(),
                admin.getNote(),
                admin.getCreate_time(),
                admin.getLogin_time(),
                admin.getStatus());
    }

    @Override
    public void destroyById(Connection conn, int id) {
        String sql = "delete from ums_admin where id = ?";
        update(conn,sql,id);
    }

    @Override
    public void update(Connection conn, UmsAdmin admin) throws Exception {
        if(admin.getId()==null) throw new NullPointerException();
        Field[] fields = admin.getClass().getDeclaredFields();
        String sql = "update ums_admin set ";
        List<Object> args = new ArrayList<>();
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            if(!name.equals("id"))
                if(field.get(admin)!=null){
                    args.add(field.get(admin));
                    sql+= name + " = ? ";
                }
        }
        args.add(admin.getId());
        sql += " where id = ?";
        update(conn,sql,args.toArray()
                );
    }

    @Override
    public UmsAdmin findById(Connection conn, int id) {
        String sql = "select * from ums_admin where id = ?";
        UmsAdmin one = findOne(conn, sql, id);
        return one;
    }

    @Override
    public List<UmsAdmin> find(Connection conn) {
        String sql = "select * from ums_admin";
        List<UmsAdmin> all = findAll(conn, sql);
        return all;
    }

    @Override
    public Long count(Connection conn) {
        String sql = "select count(*) from ums_admin";
        return getValue(conn,sql);
    }

    @Override
    public Timestamp findMaxAmount(Connection conn) {
        String sql = "select max(create_time) from ums_admin";
        return getValue(conn,sql);
    }
}
