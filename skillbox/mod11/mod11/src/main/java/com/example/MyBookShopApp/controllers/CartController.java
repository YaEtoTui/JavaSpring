package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.annotation.TimeAspectAnnotation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {

    @GetMapping("/cart")
    @TimeAspectAnnotation
    public String authorsPage(){
        return "cart";
    }
}
