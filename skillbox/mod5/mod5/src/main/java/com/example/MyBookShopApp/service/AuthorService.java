package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.domain.entity.Author;
import com.example.MyBookShopApp.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    public Map<String, List<Author>> getAuthorsMap(){
        List<Author> authorList = authorRepository.findAll();

        return authorList.stream().collect(Collectors.groupingBy((Author a)->{return a.getLastName().substring(0,1);}));
    }
}
