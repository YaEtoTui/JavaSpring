package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.annotation.TimeAspectAnnotation;
import com.example.MyBookShopApp.security.BookstoreUser;
import com.example.MyBookShopApp.security.BookstoreUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class ProfileController {

    private final BookstoreUserRepository bookstoreUserRepository;

    @Autowired
    public ProfileController(BookstoreUserRepository bookstoreUserRepository) {
        this.bookstoreUserRepository = bookstoreUserRepository;
    }

    @TimeAspectAnnotation
    @GetMapping("/profile")
    public String openProfilePage(Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        BookstoreUser bookstoreUser = bookstoreUserRepository.findBookstoreUserByEmail(email);

        model.addAttribute("name", bookstoreUser.getName());
        model.addAttribute("email", bookstoreUser.getEmail());
        model.addAttribute("phone", bookstoreUser.getPhone());
        return "profile";
    }
}
