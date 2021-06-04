package com.wsy.spring.webflux.service.impl;

import com.wsy.spring.webflux.bean.User;
import com.wsy.spring.webflux.service.UserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.HashMap;
import java.util.Map;
@Service(value = "userService")
public class UserServiceImpl implements UserService {
    private Map<Integer,User> users;
    public UserServiceImpl(){
       this.users = new HashMap<>();
       users.put(1,new User("韩国","傻逼"));
       users.put(2,new User("美国","沙雕"));
       users.put(3,new User("印度","垃圾"));
    }
    @Override
    public Mono<User> getUserById(int id) {
        return Mono.just(this.users.get(id));
    }

    @Override
    public Flux<User> getAllUser() {
        return Flux.fromIterable(this.users.values());
    }

    @Override
    public Mono<Void> saveUserInfo(Mono<User> userMono) {
        return userMono.doOnNext(user -> {
            this.users.put(users.size() + 1,user);
        }).thenEmpty(Mono.empty());//清空表示终止
    }
}
