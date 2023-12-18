package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.annotation.TimeAspectAnnotation;
import com.example.MyBookShopApp.domain.data.Book;
import com.example.MyBookShopApp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

@Controller
public class PostponedController {
    @ModelAttribute(name = "bookListPostponed")
    public List<Book> bookListPostponed() {
        return new ArrayList<>();
    }

    private final BookRepository bookRepository;

    @Autowired
    public PostponedController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/postponed")
    @TimeAspectAnnotation
    public String handlePostponedRequest(@CookieValue(value = "postponedContents", required = false) String postponedContents,
                                    Model model) {
        if (postponedContents == null || postponedContents.equals("")) {
            model.addAttribute("isPostponedEmpty", true);
        } else {
            model.addAttribute("isPostponedEmpty", false);
            postponedContents = postponedContents.startsWith("/") ? postponedContents.substring(1) : postponedContents;
            postponedContents = postponedContents.endsWith("/") ? postponedContents.substring(0, postponedContents.length() - 1) :
                    postponedContents;
            String[] cookieSlugs = postponedContents.split("/");
            List<Book> booksFromCookieSlugs = bookRepository.findBooksBySlugIn(cookieSlugs);
            model.addAttribute("bookListPostponed", booksFromCookieSlugs);
        }

        return "/postponed";
    }

    @PostMapping("/changeBookStatus/postponed/remove/{slug}")
    public String handleRemoveBookFromPostponedRequest(@PathVariable("slug") String slug, @CookieValue(name =
            "postponedContents", required = false) String postponedContents, HttpServletResponse response, Model model) {
        if (postponedContents != null && !postponedContents.equals("")) {
            ArrayList<String> cookieBooks = new ArrayList<>(Arrays.asList(postponedContents.split("/")));
            cookieBooks.remove(slug);
            Cookie cookie = new Cookie("postponedContents", String.join("/", cookieBooks));
            cookie.setPath("/postponed");
            response.addCookie(cookie);
            model.addAttribute("isPostponedEmpty", false);
        } else {
            model.addAttribute("isPostponedEmpty", true);
        }

        return "redirect:/postponed";
    }

    @PostMapping("/changeBookStatus/postponed/{slug}")
    public String handleChangeBookStatus(@PathVariable("slug") String slug, @CookieValue(name = "postponedContents",
            required = false) String postponedContents, HttpServletResponse response, Model model) {

        if (postponedContents == null || postponedContents.equals("")) {
            Cookie cookie = new Cookie("postponedContents", slug);
            cookie.setPath("/postponed");
            response.addCookie(cookie);
            model.addAttribute("isPostponedEmpty", false);
        } else if (!postponedContents.contains(slug)) {
            StringJoiner stringJoiner = new StringJoiner("/");
            stringJoiner.add(postponedContents).add(slug);
            Cookie cookie = new Cookie("postponedContents", stringJoiner.toString());
            cookie.setPath("/postponed");
            response.addCookie(cookie);
            model.addAttribute("isPostponedEmpty", false);
        }

        return "redirect:/books/" + slug;
    }
}
