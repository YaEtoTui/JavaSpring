package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bookshop")
public class AuthorPageController {
    private final AuthorService authorService;

    public AuthorPageController(AuthorService authorService) {
        this.authorService = authorService;
    }


    @GetMapping("/authors")
    public String authorsPage(Model model) {
        model.addAttribute("authorMap", authorService.getAuthorsMap());
        return "authors";
    }
}
