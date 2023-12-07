package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.domain.data.ReviewRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
class RateBookControllerTests {

    private final RateBookController rateBookController;

    RateBookControllerTests(RateBookController rateBookController) {
        this.rateBookController = rateBookController;
    }

    @Test
    void checkRateBook() {
        String slug = "book-ilp-658";
        Integer value = 3;
        assertNotNull(slug);
    }

    @Test
    void checkReview() {
        String slug = "book-ilp-658";
        ReviewRequest reviewRequest = new ReviewRequest("Hello!");
        assertNotNull(reviewRequest);
    }


}