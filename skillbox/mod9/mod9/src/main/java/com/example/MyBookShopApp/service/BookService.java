package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.domain.data.Book;
import com.example.MyBookShopApp.domain.data.Tags;
import com.example.MyBookShopApp.errors.BookstoreApiWrongParameterException;
import com.example.MyBookShopApp.repository.BookRepository;
import com.example.MyBookShopApp.repository.TagsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final TagsRepository tagsRepository;

    @Autowired
    public BookService(BookRepository bookRepository, TagsRepository tagsRepository) {
        this.bookRepository = bookRepository;
        this.tagsRepository = tagsRepository;
    }

    public List<Book> getBooksData(){
        return bookRepository.findAll();
    }

    //NEW BOOK SERVICE METHODS

    public List<Book> getBooksByAuthor(String authorName){
        return bookRepository.findBooksByAuthorFirstNameContaining(authorName);
    }

    public List<Book> getBooksByTitle(String title) throws BookstoreApiWrongParameterException {
        if(title.equals("") || title.length()<=1){
            throw new BookstoreApiWrongParameterException("Wrong values passed to one or more parameters");
        }else{
            List<Book> data = bookRepository.findBooksByTitleContaining(title);
            if(data.size()>0){
                return data;
            }else {
                throw new BookstoreApiWrongParameterException("No data found with specified parameters...");
            }
        }
    }

    public List<Book> getBooksWithPriceBetween(Integer min, Integer max){
        return bookRepository.findBooksByPriceOldBetween(min, max);
    }

    public List<Book> getBooksWithPrice(Integer price){
        return bookRepository.findBooksByPriceOldIs(price);
    }

    public List<Book> getBooksWithMaxPrice(){
        return bookRepository.getBooksWithMaxDiscount();
    }

    public List<Book> getBestsellers(){
        return bookRepository.getBestsellers();
    }

    public Page<Book> getPageofRecommendedBooks(Integer offset, Integer limit){
        Pageable nextPage = PageRequest.of(offset,limit);
        return bookRepository.findAll(nextPage);
    }

    public Page<Book> getPageofBestBooks(Integer offset, Integer limit){
        Pageable nextPage = PageRequest.of(offset,limit);
        return bookRepository.findAll(nextPage);
    }

    public Page<Book> getPageofNeoBooks(Integer offset, Integer limit){
        Pageable nextPage = PageRequest.of(offset,limit);
        return bookRepository.findAll(nextPage);
    }

    public Page<Tags> getPageofTagsBooks(Integer offset, Integer limit){
        Pageable nextPage = PageRequest.of(offset,limit);
        return tagsRepository.findAll(nextPage);
    }

    public Page<Book> getPageOfSearchResultBooks(String searchWord, Integer offset, Integer limit){
        Pageable nextPage = PageRequest.of(offset,limit);
        return bookRepository.findBookByTitleContaining(searchWord,nextPage);
    }

    public Page<Book> getPageOfGenresResultBooks(Integer id, Integer offset, Integer limit){
        Pageable nextPage = PageRequest.of(offset,limit);
        return bookRepository.findAllByTagsId(id,nextPage);
    }

    public Page<Book> getPageOfTagsResultBooks(Integer id, Integer offset, Integer limit){
        Pageable nextPage = PageRequest.of(offset,limit);
        return bookRepository.findAllByTagsId(id,nextPage);
    }

    public List<Book> getPageOfDatesResultBooks(Date date1, Date date2){
        return bookRepository.findAllByPubDateBetween(date1, date2);
    }

    public List<Book> findAllByPubDateBetween(Date date1, Date date2) {
        List<Book> bookList = bookRepository.findAll()
                .stream()
                .filter(book -> book.getPubDate().after(date1) && book.getPubDate().before(date2))
                .collect(Collectors.toList());


        return bookList;
    }

    public Page<Tags> getBooksByTags(Integer id, Integer offset, Integer limit){
        Pageable nextPage = PageRequest.of(offset,limit);
        return tagsRepository.findAllById(id, nextPage);
    }
}
