package com.wsy.spring.webflux.server;

import com.wsy.spring.webflux.handler.UserHandler;
import com.wsy.spring.webflux.service.UserService;
import com.wsy.spring.webflux.service.impl.UserServiceImpl;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.netty.http.server.HttpServer;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.toHttpHandler;

public class Server {
    //1. 创建router路由
    public RouterFunction<ServerResponse> routingFunction(){
        UserService service = new UserServiceImpl();
        UserHandler handler = new UserHandler(service);
        return RouterFunctions.route(
                GET("/users?id={id}").and(accept(APPLICATION_JSON)),handler::getUserById)
                .andRoute(GET("/users").and(accept(APPLICATION_JSON)),handler::getAllUser);
    }
    public void createReactorServer(){
        RouterFunction<ServerResponse> route = routingFunction();
        HttpHandler httpHandler = toHttpHandler(route);
        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler);
        HttpServer httpServer = HttpServer.create();
        httpServer.handle(adapter).bindNow();
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.createReactorServer();
        System.out.println("exit......");
        System.in.read();
    }
}
