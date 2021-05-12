package com.prueba;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

class AppTest {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static HomeNotLogged homenotlogged;

    @BeforeAll
    static void setUp() {
        homenotlogged = new HomeNotLogged(driver);
        driver = homenotlogged.getDriver();
        wait = new WebDriverWait(driver, 1);
        homenotlogged.navigate("https://testappautomation.herokuapp.com");
    }

    @Test
    void testApp() {
        wait.until(ExpectedConditions.titleIs("Homme Page"));
        homenotlogged.validateAssets(driver);
        homenotlogged.validateText();
    }

    @AfterAll
    static void tearDown() throws InterruptedException {
        driver.quit();
    }
}
