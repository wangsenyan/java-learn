package com.wsy.spring.webflux.handler;

import com.wsy.spring.webflux.bean.User;
import com.wsy.spring.webflux.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;


public class UserHandler {
    public final UserService service;
    public UserHandler(UserService service){
        this.service = service;
    }
    public Mono<ServerResponse> getUserById(ServerRequest req){
        Integer id = Integer.valueOf(req.queryParam("id").get()) ;
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Mono<User> useMono = this.service.getUserById(id);
        return useMono.flatMap(user
                -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromObject(user)))
                .switchIfEmpty(notFound);
    }
    public Mono<ServerResponse> getAllUser(ServerRequest req){
        Flux<User> users = this.service.getAllUser();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(users,User.class);
    }
    public Mono<ServerResponse> saveUser(ServerRequest req){
        Mono<User> userMono = req.bodyToMono(User.class);
        return ServerResponse.ok()
                .build(this.service.saveUserInfo(userMono));
    }
}
