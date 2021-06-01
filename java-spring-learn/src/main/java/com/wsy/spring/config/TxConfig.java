package com.wsy.spring.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
//<context:component-scan base-package="com.wsy.spring.jdbc"/>
@ComponentScan(basePackages = {"com.wsy.spring.jdbc"})
//<tx:annotation-driven transaction-manager="transactionManager"/>
@EnableTransactionManagement
//<context:property-placeholder location="classpath:druid.properties"/>
@PropertySource("classpath:druid.properties")
public class TxConfig {
    @Value("${user}")
    private String username;
    @Value("${password}")
    private String password;
    @Value("${url}")
    private String url;
    @Value("${driverClassName}")
    private String driverClassName;
    @Bean
    public DataSource getDruidDataSource(){
       DruidDataSource dataSource = new DruidDataSource();
       dataSource.setDriverClassName(driverClassName);
       dataSource.setUrl(url);
       dataSource.setUsername(username);
       dataSource.setPassword(password);
       return dataSource;
    }
    //自动注入了database
    @Bean
    public JdbcTemplate getJdbcTemplate(DataSource dataSource){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }
    @Bean
    public DataSourceTransactionManager getDataSourceTransactionManager(DataSource dataSource){
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(dataSource);
        return manager;
    }
}
