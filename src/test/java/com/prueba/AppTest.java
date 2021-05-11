package com.prueba;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

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
    void testApp() {
        driver.get("https://www.google.com.ar");
        WebElement busqueda = driver.findElement(By.name("q"));
        busqueda.sendKeys("Selenium");
        WebElement boton = driver.findElement(By.name("btnK"));
        boton.click();
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}
