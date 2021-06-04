package com.wsy.spring.webflux.service;

import com.wsy.spring.webflux.bean.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    public Mono<User> getUserById(int id);
    public Flux<User> getAllUser();
    public Mono<Void> saveUserInfo(Mono<User> user);
}
