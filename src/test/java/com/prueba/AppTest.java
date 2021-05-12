package com.prueba;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

class AppTest {
    private static WebDriver driver;
    private static HomeNotLogged homenotlogged;

    @BeforeAll
    static void setUp() {
        driver = homenotlogged.getDriver();
        homenotlogged = new HomeNotLogged();
        homenotlogged.navigate("https://testappautomation.herokuapp.com");
    }

    @Test
    void titleValidation() {
        assertEquals("Home Page", driver.getTitle());
    }

    @Test
    void buttonExists() {
        homenotlogged.validateAssets(driver);
    }

    @Test
    void textValidation() {
        homenotlogged.validateText();
    }

    @Test
    void validateFooterFixed() {
        homenotlogged.validateFooter();
    }

    @AfterAll
    static void tearDown() throws InterruptedException {
        driver.quit();
    }
}
