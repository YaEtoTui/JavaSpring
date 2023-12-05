package com.example.MyBookShopApp.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GenrePage {

    private String url = "http://localhost:8085/genres/index";
    private ChromeDriver driver;

    public GenrePage(ChromeDriver driver) {
        this.driver = driver;
    }

    public GenrePage callPage() {
        driver.get(url);
        return this;
    }

    public GenrePage pause() throws InterruptedException {
        Thread.sleep(2000);
        return this;
    }

    public GenrePage setUpSearchToken(String token) {
        WebElement element = driver.findElement(By.id("query"));
        element.sendKeys(token);
        return this;
    }

    public GenrePage submit() {
        WebElement element = driver.findElement(By.id("search"));
        element.submit();
        return this;
    }
}
