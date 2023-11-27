package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.domain.data.Book;
import com.example.MyBookShopApp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BooksRatingAndPopulatityService {

    private final BookRepository bookRepository;

    @Autowired
    public BooksRatingAndPopulatityService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Page<Book> getBestsellers(Integer offset, Integer limit){
        Pageable page = PageRequest.of(offset, limit);
        //как бы тут и так в бд есть значение показывающие популярный или нет, зачем формула? :|
        return bookRepository.findAllByIsBesteller(1, page);
    }
}
