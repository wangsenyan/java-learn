package com.wsy.basis.aop.dynamic.proxy.impl;

import com.wsy.basis.aop.dynamic.proxy.SmsService;

public class SmsServiceImpl implements SmsService {
    @Override
    public String send(String message) {
        System.out.println("send message:" + message);
        return message;
    }

    @Override
    public String ping(String ip) {
        System.out.println("ping ip: " + ip);
        return ip;
    }
}
