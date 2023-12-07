package com.example.MyBookShopApp.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class NewPage {

    private String url = "http://localhost:8085/books/recent";
    private ChromeDriver driver;

    public NewPage(ChromeDriver driver) {
        this.driver = driver;
    }

    public NewPage callPage() {
        driver.get(url);
        return this;
    }

    public NewPage pause() throws InterruptedException {
        Thread.sleep(2000);
        return this;
    }

    public NewPage setUpSearchToken(String token) {
        WebElement element = driver.findElement(By.id("query"));
        element.sendKeys(token);
        return this;
    }

    public NewPage submit() {
        WebElement element = driver.findElement(By.id("search"));
        element.submit();
        return this;
    }
}
