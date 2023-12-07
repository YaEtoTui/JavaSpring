package com.example.MyBookShopApp.security.exception;

public class UsernameNotFoundException extends RuntimeException {

    public UsernameNotFoundException(String msg) {
        super(msg);
    }
}
