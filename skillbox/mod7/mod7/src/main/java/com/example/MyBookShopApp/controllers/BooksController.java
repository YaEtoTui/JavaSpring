package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.domain.data.Book;
import com.example.MyBookShopApp.domain.data.ResourceStorage;
import com.example.MyBookShopApp.domain.data.ReviewRequest;
import com.example.MyBookShopApp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.logging.Logger;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookRepository bookRepository;

    private final ResourceStorage storage;


    @Autowired
    public BooksController(BookRepository bookRepository,ResourceStorage storage) {
        this.bookRepository = bookRepository;
        this.storage = storage;
    }

    @GetMapping("/{slug}")
    public String bookPage(@PathVariable("slug") String slug, Model model) {
        Book book = bookRepository.findBookBySlug(slug);
        model.addAttribute("slugBook", book);
        model.addAttribute("reviewRequest", new ReviewRequest());
        return "/books/slug";
    }

    @PostMapping("/{slug}/img/save")
    public String saveNewBookImage(@RequestParam("file") MultipartFile file, @PathVariable("slug")String slug) throws IOException {
        String savePath = storage.saveNewBookImage(file,slug);
        Book bookToUpdate = bookRepository.findBookBySlug(slug);
        bookToUpdate.setImage(savePath);
        bookRepository.save(bookToUpdate); //save new path in db here

        return "redirect:/books/"+slug;
    }

    @GetMapping("/download/{hash}")
    public ResponseEntity<ByteArrayResource> bookFile(@PathVariable("hash")String hash) throws IOException{
        Path path = storage.getBookFilePath(hash);
        Logger.getLogger(this.getClass().getSimpleName()).info("book file path: "+path);

        MediaType mediaType = storage.getBookFileMime(hash);
        Logger.getLogger(this.getClass().getSimpleName()).info("book file mime type: "+mediaType);

        byte[] data = storage.getBookFileByteArray(hash);
        Logger.getLogger(this.getClass().getSimpleName()).info("book file data len: "+data.length);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+path.getFileName().toString())
                .contentType(mediaType)
                .contentLength(data.length)
                .body(new ByteArrayResource(data));
    }

    @ModelAttribute(name = "bookListReview")
    public List<String> bookListReview() {
        return new ArrayList<>();
    }

    @PostMapping("/checkReview/{slug}")
    public String checkReview(@PathVariable("slug") String slug, ReviewRequest reviewRequest, @CookieValue(name =
            "reviewContents", required = false) String reviewContents, HttpServletResponse response, Model model) {
        model.addAttribute("bookListReview", bookListReview().add(reviewRequest.getReview()));

        if (reviewContents == null || reviewContents.equals("")) {
            Cookie cookie = new Cookie("reviewContents", slug);
            cookie.setPath("/books");
            response.addCookie(cookie);
            model.addAttribute("isReviewEmpty", false);
        } else if (!reviewContents.contains(slug)) {
            StringJoiner stringJoiner = new StringJoiner("/");
            stringJoiner.add(reviewContents).add(slug);
            Cookie cookie = new Cookie("reviewContents", stringJoiner.toString());
            cookie.setPath("/reviews");
            response.addCookie(cookie);
            model.addAttribute("isReviewEmpty", false);
        }
        return "redirect:/books/" + slug;
    }
}
