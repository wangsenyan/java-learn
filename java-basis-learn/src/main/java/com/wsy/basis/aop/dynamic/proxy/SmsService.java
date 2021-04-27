package com.wsy.basis.aop.dynamic.proxy;

public interface SmsService {
    String send(String message);
    String ping(String ip);
}
