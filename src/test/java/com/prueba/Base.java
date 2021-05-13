package com.prueba;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
    private WebDriver driver;
    protected WebDriverWait wait;

    public Base() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");

        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver(options);
        this.wait = new WebDriverWait(driver, 3);
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
