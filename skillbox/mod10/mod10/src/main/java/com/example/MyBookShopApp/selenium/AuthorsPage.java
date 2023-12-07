package com.example.MyBookShopApp.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AuthorsPage {

    private String url = "http://localhost:8085/authors/index";
    private ChromeDriver driver;

    public AuthorsPage(ChromeDriver driver) {
        this.driver = driver;
    }

    public AuthorsPage callPage() {
        driver.get(url);
        return this;
    }

    public AuthorsPage pause() throws InterruptedException {
        Thread.sleep(2000);
        return this;
    }

    public AuthorsPage setUpSearchToken(String token) {
        WebElement element = driver.findElement(By.id("query"));
        element.sendKeys(token);
        return this;
    }

    public AuthorsPage submit() {
        WebElement element = driver.findElement(By.id("search"));
        element.submit();
        return this;
    }
}
