package com.example.MyBookShopApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.StringJoiner;

@Controller
public class RateBookController {

    @PostMapping("/rateBook/{slug}")
    public String checkRateBook(@PathVariable("slug") String slug, @CookieValue(name = "rateContents",
            required = false) String rateContents, HttpServletResponse response, Model model) {

        if (rateContents == null || rateContents.equals("")) {
            Cookie cookie = new Cookie("rateContents", slug);
            cookie.setPath("/rate");
            response.addCookie(cookie);
            model.addAttribute("isRateBookEmpty", false);
        } else if (!rateContents.contains(slug)) {
            StringJoiner stringJoiner = new StringJoiner("/");
            stringJoiner.add(rateContents).add(slug);
            Cookie cookie = new Cookie("rateContents", stringJoiner.toString());
            cookie.setPath("/rate");
            response.addCookie(cookie);
            model.addAttribute("isRateBookEmpty", false);
        }

        return "redirect:/books/" + slug;
    }
}
