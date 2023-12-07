package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.annotation.TimeAspectAnnotation;
import com.example.MyBookShopApp.domain.data.BooksPageDto;
import com.example.MyBookShopApp.domain.data.Genre;
import com.example.MyBookShopApp.repository.GenreRepository;
import com.example.MyBookShopApp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class GenresController {

    GenreRepository genreRepository;
    BookService bookService;

    @Autowired
    public GenresController(GenreRepository genreRepository, BookService bookService) {
        this.genreRepository = genreRepository;
        this.bookService = bookService;
    }

    @ModelAttribute("listGenres")
    public List<Genre> listGenres() {
        return genreRepository.findAll();
    }

    @ModelAttribute("listBooksGenre")
    public List<Genre> listBooksGenre() {
        return new ArrayList<>();
    }

    @GetMapping("/genres/index")
    @TimeAspectAnnotation
    public String genrePage(Model model){
        return "genres/index";
    }

    @GetMapping("/genres/slug/{id}")
    @TimeAspectAnnotation
    public String slugPage(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("genre", genreRepository.getById(id));
        model.addAttribute("listBooksGenre", bookService.getPageOfGenresResultBooks(id, 0, 5).getContent());
        return "/genres/slug";
    }

    @GetMapping("/books/genre/{id}")
    @ResponseBody
    public BooksPageDto getSlugNextPage(@RequestParam("offset") Integer offset,
                                     @RequestParam("limit") Integer limit,
                                     @PathVariable(value = "id", required = false) Integer id) {
        return new BooksPageDto(bookService.getPageOfGenresResultBooks(id, offset, limit).getContent());
    }

}
