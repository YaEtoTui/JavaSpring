package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.domain.data.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findBooksByAuthor_FirstName(String name);

    @Query("from Book")
    List<Book> customFindAllBooks();

    //NEW BOOK REST REPOSITORY

    List<Book> findBooksByAuthorFirstNameContaining(String authorsFirstName);

    List<Book> findBooksByTitleContaining(String bookTitle);

    List<Book> findBooksByPriceOldBetween(Integer min, Integer max);

    List<Book> findBooksByPriceOldIs(Integer price);

    @Query("from Book where is_Bestseller=1")
    List<Book> getBestsellers();

    Page<Book> findAllByGenreId(Integer id, Pageable pageable);

    Page<Book> findAllByIsBesteller(Integer number, Pageable nextPage);

    @Query(value = "SELECT * FROM books WHERE discount = (SELECT MAX(discount) FROM books",nativeQuery = true)
    List<Book> getBooksWithMaxDiscount();

    Page<Book> findBookByTitleContaining(String bookTitle, Pageable nextPage);

    List<Book> findAllByPubDateBetween(Date date1, Date data2);

    List<Book> findAllByGenre_Name(String nameGenre);

    Page<Book> findAllByTagsId(Integer id, Pageable nextPage);

    Book findBookBySlug(String slug);

    Book findByTitleContaining(String title);

    List<Book> findBooksBySlugIn(String[] slugs);
}
