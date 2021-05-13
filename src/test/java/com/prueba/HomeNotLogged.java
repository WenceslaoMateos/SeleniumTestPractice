package com.prueba;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomeNotLogged extends Base {
    private By logginbutton = By.id("loginBTN");
    private By lorenlink = By.linkText("Lorem Ipsum");
    private By formlink = By.linkText("Testing Forms");
    private By notloggedtitle = By.id("notLoggedTitleTXT");
    private By notloggedscreen = By.id("notLoggedScreen");

    public void validateAssets(WebDriver driver) {
        assertTrue(isDisplayed(this.logginbutton));
        assertTrue(isDisplayed(this.lorenlink));
        assertTrue(isDisplayed(this.formlink));
        assertTrue(isDisplayed(this.notloggedtitle));
        assertTrue(isDisplayed(this.notloggedscreen));
    }

    public void validateText() {
        assertEquals("Welcome to my Automation Testing Site", this.findElement(By.id("notLoggedTitleTXT")).getText());
        assertEquals("Please click Login button to log into the application or sign up!",
                this.findElement(By.id("notLoggedScreen")).getText());
        assertEquals(
                "Disclaimer: This project is a personal site meant to be used as a help test site to be able to perform some automation test on demand.",
                this.findElement(By.id("footerbox")).getText());
        assertEquals("Log In", this.findElement(By.id("loginBTN")).getText());
    }

    public void validateFooter() {
        assertEquals(this.findElement(By.tagName("footer")).getCssValue("position"), "fixed");
        assertEquals(this.findElement(By.tagName("footer")).getCssValue("bottom"), "0px");
    }

    public WebElement getLogginbutton() {
        return this.findElement(this.logginbutton);
    }

    public void validateHyperlinks() {
        this.navigate("https://testappautomation.herokuapp.com/forms/");
        assertThrows(org.openqa.selenium.TimeoutException.class, () -> {
            this.wait.until(ExpectedConditions.presenceOfElementLocated(By.id("BTNSubmit")));
        });
        this.navigate("https://testappautomation.herokuapp.com/lorem/");
        assertThrows(org.openqa.selenium.TimeoutException.class, () -> {
            this.wait.until(ExpectedConditions.presenceOfElementLocated(By.id("news")));
        });
    }
}
