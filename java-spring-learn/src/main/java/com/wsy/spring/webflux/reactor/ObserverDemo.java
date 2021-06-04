package com.wsy.spring.webflux.reactor;

import java.util.Observable;

/**
 * 数据流的变化和传播
 * java8 Observer,Observable
 * java9 Flow(发布/订阅)
 */
public class ObserverDemo extends Observable {
    public static void main(String[] args) {
        ObserverDemo observer = new ObserverDemo();
        //添加观察者
        observer.addObserver((a,g)->{
            System.out.println("尼玛的");
        });
        observer.addObserver((a,g)->{
            System.out.println("fuck you");
        });
        observer.setChanged();//数据变化
        observer.notifyObservers();//通知
    }
}
