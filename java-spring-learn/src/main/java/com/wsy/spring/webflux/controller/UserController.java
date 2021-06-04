package com.wsy.spring.webflux.controller;

import com.wsy.spring.webflux.bean.User;
import com.wsy.spring.webflux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    public Mono<User> getUserById(Integer id){
        return userService.getUserById(id);
    }
}
