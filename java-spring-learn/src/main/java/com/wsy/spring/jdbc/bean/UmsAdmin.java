package com.wsy.spring.jdbc.bean;

import lombok.Data;

import java.sql.Blob;
import java.util.Date;
@Data
public class UmsAdmin {
    Long id;
    String username;
    String password;
    String icon;
    String email;
    String nick_name;
    String note;
    Date create_time;
    Date login_time;
    Integer status;
    Blob picture;
}
