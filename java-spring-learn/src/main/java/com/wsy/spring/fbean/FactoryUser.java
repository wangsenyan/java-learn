package com.wsy.spring.fbean;

import com.wsy.spring.bean.User;
import org.springframework.beans.factory.FactoryBean;

public class FactoryUser implements FactoryBean<User> {
    //定义返回bean
    @Override
    public User getObject() throws Exception {
        User user = new User();
        user.setName("王曼");
        user.setAuthor("周曼");
        return user;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }
}
