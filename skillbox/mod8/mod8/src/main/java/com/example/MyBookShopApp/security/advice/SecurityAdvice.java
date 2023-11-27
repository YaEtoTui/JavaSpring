package com.example.MyBookShopApp.security.advice;

import com.example.MyBookShopApp.security.exception.BadRequestException;
import com.example.MyBookShopApp.security.exception.UsernameNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

//task_1
@RestControllerAdvice
public class SecurityAdvice {

    @ExceptionHandler({UsernameNotFoundException.class, })
    public ResponseEntity<String> notUsername(Exception e) {
        return status(OK)
                .body(e.getMessage());
    }

    @ExceptionHandler({BadRequestException.class, })
    public ResponseEntity<String> badRequest(Exception e) {
        return status(BAD_REQUEST)
                .body(e.getMessage());
    }
}
