package com.wsy.algorithm.niuke;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class AuthenticationManager {
    HashMap<String,Integer> tokenExpire;
    int timeToLive;
    public AuthenticationManager(int timeToLive) {
        tokenExpire = new HashMap<>();
        this.timeToLive = timeToLive;
    }

    public void generate(String tokenId, int currentTime) {
        tokenExpire.put(tokenId,currentTime+timeToLive);
    }

    public void renew(String tokenId, int currentTime) {
        Integer time = tokenExpire.get(tokenId);
        if(time!=null && currentTime<time)
            tokenExpire.put(tokenId,currentTime+timeToLive);
    }

    public int countUnexpiredTokens(int currentTime) {
        AtomicInteger res = new AtomicInteger();
       tokenExpire.forEach((token,expire)->{
           if(expire>=currentTime) res.getAndIncrement();
       });
       return res.get();
    }
}
