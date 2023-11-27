package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.domain.data.Book;
import com.example.MyBookShopApp.domain.data.BooksPageDto;
import com.example.MyBookShopApp.service.BookService;
import com.example.MyBookShopApp.service.BooksRatingAndPopulatityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PopularController {

    private final BooksRatingAndPopulatityService booksRatingAndPopulatityService;

    @Autowired
    public PopularController(BooksRatingAndPopulatityService booksRatingAndPopulatityService) {
        this.booksRatingAndPopulatityService = booksRatingAndPopulatityService;
    }

//    @ModelAttribute("bestBooks")
//    public List<Book> bestBooks(){
//        return booksRatingAndPopulatityService.getBestsellers();
//    }

    @ModelAttribute("bestBooks")
    public List<Book> listBestBooks() {
        return new ArrayList<>();
    }

    @GetMapping("/books/popular")
    public String popularPage(Model model){
        model.addAttribute("bestBooks", booksRatingAndPopulatityService.getBestsellers(0, 5).getContent());
        return "books/popular";
    }

    @GetMapping("/books/popular/index")
    @ResponseBody
    public BooksPageDto getNextBestPage(@RequestParam("offset") Integer offset,
                                        @RequestParam("limit") Integer limit) {
        return new BooksPageDto(booksRatingAndPopulatityService.getBestsellers(offset, limit).getContent());
    }
}
