package com.example.MyBookShopApp.domain.data;

public class ReviewRequest {
    String review;

    public ReviewRequest() {
    }

    public ReviewRequest(String review) {
        this.review = review;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return "ReviewRequest{" +
                "review='" + review + '\'' +
                '}';
    }
}
