package com.example.rediscachefailurehandling;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TokenRepository {

    @Cacheable("tokens")
    public Token find(String id) {
        System.out.println("---> Cache miss for id: '" + id + "'");
        return verySlowGenerateToken(id);
    }

    private static Token verySlowGenerateToken(String id) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Token(id, UUID.randomUUID().toString(), 0);
    }
}
