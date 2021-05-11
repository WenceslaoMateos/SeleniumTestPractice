package com.prueba;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

class AppTest {

    static WebDriver driver;

    @BeforeAll
    static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    void testApp() throws InterruptedException {
        driver.get("https://www.google.com.ar");
        WebElement busqueda = driver.findElement(By.name("q"));
        busqueda.sendKeys("Selenium");
        WebElement boton = driver.findElement(By.name("btnK"));
        boton.submit();
        WebElement res = driver.findElement(By.partialLinkText("Selenium"));
        res.click();
        assertEquals(driver.getTitle(),"SeleniumHQ Browser Automation");
        Thread.sleep(3000);
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}
