package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.domain.data.BooksPageDto;
import com.example.MyBookShopApp.domain.data.SearchWordDto;
import com.example.MyBookShopApp.domain.data.Book;
import com.example.MyBookShopApp.domain.data.Tags;
import com.example.MyBookShopApp.repository.TagsRepository;
import com.example.MyBookShopApp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainPageController {

    private final BookService bookService;
    private final TagsRepository tagsRepository;

    @Autowired
    public MainPageController(BookService bookService, TagsRepository tagsRepository) {
        this.bookService = bookService;
        this.tagsRepository = tagsRepository;
    }

    @ModelAttribute("recommendedBooks")
    public List<Book> recommendedBooks() {
        return bookService.getPageofRecommendedBooks(0, 9).getContent();
    }

    @ModelAttribute("neoBooks")
    public List<Book> neoBooks() {
        return bookService.getPageofNeoBooks(0, 10).getContent();
    }

    @ModelAttribute("bestBooks")
    public List<Book> bestBooks() {
        return bookService.getBestsellers();
    }

    @ModelAttribute("listTags")
    public List<Tags> listTags() {
        return bookService.getPageofTagsBooks(0, 5).getContent();
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @ModelAttribute("searchResults")
    public List<Book> searchResults() {
        return new ArrayList<>();
    }

    @ModelAttribute("listTagBooks")
    public List<Book> listTagBooks() {
        return new ArrayList<>();
    }

    @GetMapping("/")
    public String mainPage(Model model) {
        return "index";
    }


    @GetMapping("/books/recommended")
    @ResponseBody
    public BooksPageDto getBooksPage(@RequestParam("offset") Integer offset,
                                     @RequestParam("limit") Integer limit) {
        return new BooksPageDto(bookService.getPageofRecommendedBooks(offset, limit).getContent());
    }

    @GetMapping(value = {"/search", "/search/{searchWord}"})
    public String getSearchResult(@PathVariable(value = "searchWord", required = false) SearchWordDto searchWordDto,
                                  Model model) {
        model.addAttribute("searchWordDto", searchWordDto);
        model.addAttribute("searchResults",
                bookService.getPageOfSearchResultBooks(searchWordDto.getExample(), 0, 5).getContent());
        return "/search/index";
    }

    @GetMapping("/search/page/{searchWord}")
    @ResponseBody
    public BooksPageDto getNextSearchPage(@RequestParam("offset") Integer offset,
                                          @RequestParam("limit") Integer limit,
                                          @PathVariable(value = "searchWord", required = false) SearchWordDto searchWordDto) {
        return new BooksPageDto(bookService.getPageOfSearchResultBooks(searchWordDto.getExample(), offset, limit).getContent());
    }

    @GetMapping("/books/tag/{id}")
    @ResponseBody
    public BooksPageDto getNextTagsPage(@RequestParam("offset") Integer offset,
                                          @RequestParam("limit") Integer limit,
                                          @PathVariable(value = "id", required = false) Integer id) {
        return new BooksPageDto(bookService.getPageOfTagsResultBooks(id, offset, limit).getContent());
    }

    @GetMapping("/books/tags/{id}")
    public String tagsPage(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("tag", tagsRepository.getById(id));
        model.addAttribute("listTagBooks", bookService.getPageOfTagsResultBooks(id, 0, 5).getContent());
        return "/tags/index";
    }
}
