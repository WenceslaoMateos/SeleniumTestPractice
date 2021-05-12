package com.prueba;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
    private WebDriver driver;

    public Base(WebDriver driver) {
        this.driver = driver;
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public String getText(WebElement element) {
        return element.getText();
    }

    public void setText(String inputText, By locator) {
        this.driver.findElement(locator).sendKeys(inputText);
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
