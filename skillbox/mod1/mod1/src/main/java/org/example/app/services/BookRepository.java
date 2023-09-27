package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository implements ProjectRepository<Book> {

    private final Logger logger = Logger.getLogger(BookRepository.class);
    private final List<Book> repo = new ArrayList<>();

    @Override
    public List<Book> retreiveAll() {
        return new ArrayList<>(repo);
    }

    @Override
    public boolean store(Book book) {
        if (!book.getAuthor().isEmpty() || !book.getTitle().isEmpty() || book.getSize() != null) {
            book.setId(book.hashCode());
            logger.info("store new book: " + book);
            return repo.add(book);
        } else {
            logger.info("Failed to save the book. At least 1 field must be filled in");
            return false;
        }

    }

    @Override
    public boolean removeItemById(Integer bookIdToRemove) {
        for (Book book : retreiveAll()) {
            if (book.getId().equals(bookIdToRemove)) {
                logger.info("remove book completed: " + book);
                return repo.remove(book);
            }
        }
        return false;
    }

    @Override
    public void removeItemByRegex(String regexToRemove) {
        int countRemovedBooks = 0;
        for (Book book : retreiveAll()) {
            if (book.getTitle().equals(regexToRemove)
                    || book.getAuthor().equals(regexToRemove)
                    || checkBookIsInteger(book, regexToRemove)) {
                logger.info("remove book completed: " + book);
                countRemovedBooks++;
                repo.remove(book);
            }
        }
        logger.info("Books have been removed: " + countRemovedBooks);
    }

    /*проверяем, что строка типа Integer и является размером книги*/
    private boolean checkBookIsInteger(Book book, String regexToRemove) {
        if (regexToRemove.isEmpty())
            return false;
        for (int i = 0; i < regexToRemove.length(); i++) {
            char c = regexToRemove.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return book.getSize() == Integer.parseInt(regexToRemove);
    }
}
