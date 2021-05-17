package com.wsy.spring.jdbc.bean;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class UmsAdminLog {
    Long id;
    Long admin_id;
    Timestamp create_time;
    String ip;
    String address;
    String user_agent;
}
