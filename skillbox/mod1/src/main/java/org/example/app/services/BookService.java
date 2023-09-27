package org.example.app.services;

import org.example.web.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final ProjectRepository<Book> bookRepo;

    @Autowired
    public BookService(ProjectRepository<Book> bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getAllBooks() {
        return bookRepo.retreiveAll();
    }

    public boolean saveBook(Book book) {
        return bookRepo.store(book);
    }

    public boolean removeBookById(String bookIdToRemove) {
        return bookRepo.removeItemById(bookIdToRemove);
    }

    public void removeBookByRegex(String regexToRemove) {
        bookRepo.removeItemByRegex(regexToRemove);
    }
}
