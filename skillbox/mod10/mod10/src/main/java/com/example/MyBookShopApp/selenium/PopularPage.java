package com.example.MyBookShopApp.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class PopularPage {

    private String url = "http://localhost:8085/books/popular";
    private ChromeDriver driver;

    public PopularPage(ChromeDriver driver) {
        this.driver = driver;
    }

    public PopularPage callPage() {
        driver.get(url);
        return this;
    }

    public PopularPage pause() throws InterruptedException {
        Thread.sleep(2000);
        return this;
    }

    public PopularPage setUpSearchToken(String token) {
        WebElement element = driver.findElement(By.id("query"));
        element.sendKeys(token);
        return this;
    }

    public PopularPage submit() {
        WebElement element = driver.findElement(By.id("search"));
        element.submit();
        return this;
    }
}
