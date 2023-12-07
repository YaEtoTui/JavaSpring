package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.domain.data.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {

}
