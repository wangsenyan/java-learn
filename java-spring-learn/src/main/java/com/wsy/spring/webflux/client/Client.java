package com.wsy.spring.webflux.client;

import com.wsy.spring.webflux.bean.User;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class Client {
    public static void main(String[] args) {
        WebClient webClient = WebClient.create("http://127.0.0.1:49659");
        String id = "1";
        //block订阅
        User result = webClient.get().uri("/users/id={id}", id).accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(User.class).block();
        System.out.println(result);
    }
}
