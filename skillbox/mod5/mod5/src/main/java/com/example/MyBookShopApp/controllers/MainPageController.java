package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.domain.entity.Book;
import com.example.MyBookShopApp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class MainPageController {

    private final BookRepository bookRepository;

    @Autowired
    public MainPageController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @ModelAttribute("recommendedBooks")
    public List<Book> recommendedBooks(){
        return bookRepository.findAll();
    }

    @GetMapping("/")
    public String mainPage(Model model){
        return "index";
    }



}
