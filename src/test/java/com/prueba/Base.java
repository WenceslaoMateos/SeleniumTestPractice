package com.prueba;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
    private WebDriver driver;

    public Base() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");

        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver(options);
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public WebElement findElement(By locator) {
        return this.driver.findElement(locator);
    }

    public boolean isDisplayed(By locator) {
        try {
            return this.driver.findElement(locator).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void navigate(String url) {
        this.driver.get(url);
    }
}
