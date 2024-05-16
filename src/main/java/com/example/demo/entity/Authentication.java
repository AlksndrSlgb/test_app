package com.example.demo.entity;

public class Authentication {

    private final String token;

    public Authentication(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}