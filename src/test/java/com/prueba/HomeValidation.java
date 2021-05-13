package com.prueba;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

class HomeValidation {
    private static WebDriver driver;
    private static HomeNotLogged homenotlogged;

    @BeforeAll
    static void setUp() {
        homenotlogged = new HomeNotLogged();
        driver = homenotlogged.getDriver();
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

    @Test
    void validateHyperlinks() {
        homenotlogged.validateHyperlinks();
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}
