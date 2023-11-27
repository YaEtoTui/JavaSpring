package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.domain.data.BooksPageDto;
import com.example.MyBookShopApp.domain.data.DatesDto;
import com.example.MyBookShopApp.domain.data.SearchWordDto;
import com.example.MyBookShopApp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class RecentController {

    private final BookService bookService;

    @Autowired
    public RecentController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("datesDto")
    public DatesDto searchDatesDto() {
        return new DatesDto();
    }

    @GetMapping(value = "/books/recent/dates")
    public String getDatesResult(@PathVariable(value = "datesDto", required = false) DatesDto datesDto,
                                  Model model) {
        model.addAttribute("neoBooks", bookService.getPageOfDatesResultBooks(datesDto.getDate1(), datesDto.getDate2()));

        return "/books/recent";
    }

    @GetMapping("/books/recent/index")
    public String recentPage(Model model){
        model.addAttribute("neoBooks", bookService.getPageofNeoBooks(0, 5).getContent());
        return "books/recent";
    }

    @GetMapping("/books/recent")
    @ResponseBody
    public BooksPageDto getNextNeoPage() {
        return new BooksPageDto(bookService.getPageofNeoBooks(0, 5).getContent());
    }
}
