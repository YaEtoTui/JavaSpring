package com.example.MyBookShopApp.security;

import java.time.LocalDateTime;

public class Transaction {
    LocalDateTime date;
    String count;
    String description;

    public Transaction(CountForm countForm) {
        this.date = LocalDateTime.now();
        this.count = String.format("+%s р.", countForm.getCount());
        this.description = "Пополнение счета";
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
