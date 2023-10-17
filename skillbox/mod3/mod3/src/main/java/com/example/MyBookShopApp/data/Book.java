package com.example.MyBookShopApp.data;

import javax.persistence.Column;

public class Book {

    private Integer id;

    @Column(name = "author_id")
    private Integer authorId;
    private String title;
    @Column(name = "price_old")
    private Integer priceOld;
    private Integer price;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + authorId + '\'' +
                ", title='" + title + '\'' +
                ", priceOld=" + priceOld +
                ", price=" + price +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPriceOld() {
        return priceOld;
    }

    public void setPriceOld(Integer priceOld) {
        this.priceOld = priceOld;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
