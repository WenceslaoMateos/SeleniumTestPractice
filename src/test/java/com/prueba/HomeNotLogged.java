package com.prueba;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomeNotLogged extends Base {
    private By logginbutton = By.id("loginBTN");
    private By lorenlink = By.linkText("Lorem Ipsum");
    private By formlink = By.linkText("Testing Forms");
    private By notloggedtitle = By.id("notLoggedTitleTXT");
    private By notloggedscreen = By.id("notLoggedScreen");

    public HomeNotLogged(WebDriver driver) {
        super(driver);
    }

    public void validateAssets(WebDriver driver) {
        assertTrue(isDisplayed(this.logginbutton));
        assertTrue(isDisplayed(this.lorenlink));
        assertTrue(isDisplayed(this.formlink));
        assertTrue(isDisplayed(this.notloggedtitle));
        assertTrue(isDisplayed(this.notloggedscreen));
    }

    public void validateText() {
        assertEquals("Welcome to my Atomation Testing Site", this.findElement(By.id("notLoggedTitleTXT")).getText());
        assertEquals("Please click Login button to log into the application or sign up!",
                this.findElement(By.id("notLoggedScreen")).getText());
    }
}
