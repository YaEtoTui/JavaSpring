package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.domain.data.ReviewRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Controller
@RequestMapping("/user")
public class ReviewController {

    @ModelAttribute(name = "bookListReview")
    public List<String> bookListReview() {
        return new ArrayList<>();
    }

    @PostMapping("/books/checkReview/{slug}")
    public String checkReview(@PathVariable("slug") String slug, ReviewRequest reviewRequest, @CookieValue(name =
            "reviewContents", required = false) String reviewContents, HttpServletResponse response, Model model) {
        System.out.println(bookListReview().add(reviewRequest.getReview()));
        model.addAttribute("bookListReview", bookListReview().add(reviewRequest.getReview()));
        if (reviewContents == null || reviewContents.equals("")) {
            Cookie cookie = new Cookie("reviewContents", slug);
            cookie.setPath("/books");
            response.addCookie(cookie);
            model.addAttribute("isReviewEmpty", false);
        } else if (!reviewContents.contains(slug)) {
            StringJoiner stringJoiner = new StringJoiner("/");
            stringJoiner.add(reviewContents).add(slug);
            Cookie cookie = new Cookie("reviewContents", stringJoiner.toString());
            cookie.setPath("/reviews");
            response.addCookie(cookie);
            model.addAttribute("isReviewEmpty", false);
        }
        return "redirect:/books/" + slug;
    }
}
