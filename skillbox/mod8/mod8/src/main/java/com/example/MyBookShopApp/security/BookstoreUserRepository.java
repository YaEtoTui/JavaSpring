package com.example.MyBookShopApp.security;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookstoreUserRepository extends JpaRepository<BookstoreUser, String> {

    BookstoreUser findBookstoreUserByEmail(String email);

    BookstoreUser findBookstoreUserByName(String name);
}
