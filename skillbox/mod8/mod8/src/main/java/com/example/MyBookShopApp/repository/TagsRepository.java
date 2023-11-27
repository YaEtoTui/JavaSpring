package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.domain.data.Tags;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagsRepository extends JpaRepository<Tags, Integer> {

    Page<Tags> findAllById(Integer id, Pageable nextPage);
}
