package com.prueba;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FormsValidation {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    static void setUp() {
        // Este bloque es para que no aparezca el navegador en cada prueba
        // ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless");

        // Generamos y seteamos la instancia del navegador
        WebDriverManager.chromedriver().setup();
        // driver = new ChromeDriver(options);
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 3);
    }

    @BeforeEach
    void setUpEach() {
        // Vamos a entrar a la pagina prncipal cada vez que querramos ejecutar una
        // prueba, para evitar el problema de que quede alguin logueado
        driver.get("https://testappautomation.herokuapp.com");
        // Siempre vamos a comenzar en la pagina de logueo
        driver.findElement(By.id("loginBTN")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("lelapassano@gmail.com");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("Automation123!");
        driver.findElement(By.name("action")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("logoutBTN")));
        driver.get("https://testappautomation.herokuapp.com/forms/");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("logoutBTN")));
    }

    @Test
    void headerFooterValidation() {
        WebElement formheader;
        WebElement formfooter;
        formheader = driver.findElement(By.tagName("nav"));
        formfooter = driver.findElement(By.tagName("footer"));
        driver.get("https://testappautomation.herokuapp.com");
        formheader.equals(driver.findElement(By.tagName("nav")));
        formfooter.equals(driver.findElement(By.tagName("footer")));
        driver.get("https://testappautomation.herokuapp.com/lorem/");
        formheader.equals(driver.findElement(By.tagName("nav")));
        formfooter.equals(driver.findElement(By.tagName("footer")));
    }

    @Test
    void titleValidation() {
        assertEquals("Forms Page", driver.getTitle());
    }

    @Test
    void fillControlsValidation() {
        ArrayList<HashMap<String, String>> lista = createControls();
        Iterator<HashMap<String, String>> i = lista.iterator();
        HashMap<String, String> actual;
        Select select;

        while (i.hasNext()) {
            actual = i.next();
            driver.findElement(By.id("TXTUser")).sendKeys(actual.get("nombre"));
            driver.findElement(By.id("TXTPass")).sendKeys(actual.get("apellido"));
            driver.findElement(By.id("TXTNick")).sendKeys(actual.get("apodo"));
            driver.findElement(By.id("TXTEmail")).sendKeys(actual.get("email"));
            driver.findElement(By.id("TXTUrl")).sendKeys(actual.get("personalsite"));
            driver.findElement(By.id("TXTMobile")).sendKeys(actual.get("mobileNumber"));

            select = new Select(driver.findElement(By.id("SELTitle")));
            select.selectByValue(actual.get("title"));

            driver.findElement(By.id("TXTAbout")).sendKeys(actual.get("something"));

            if (actual.get("?").equals("Si")) {
                driver.findElement(By.id("RADButDevYes")).click();
            } else {
                driver.findElement(By.id("RADButDevNo")).click();
            }
            driver.findElement(By.id("BTNSubmit")).click();
            assertDoesNotThrow(() -> {
                wait.until(ExpectedConditions.urlToBe("https://testappautomation.herokuapp.com/result/"));
            });
            assertEquals("Result Page", driver.getTitle());// Este da error
            assertEquals(actual.get("title"), driver.findElement(By.id("Title")).getText());
            assertEquals(actual.get("nombre"), driver.findElement(By.id("FirstName")).getText());
            assertEquals(actual.get("apellido"), driver.findElement(By.id("LastName")).getText());
            assertEquals(actual.get("apodo"), driver.findElement(By.id("NickName")).getText());// Este da error
            assertEquals(actual.get("?").toLowerCase(), driver.findElement(By.id("Developer")).getText());
            assertEquals(actual.get("mobileNumber"), driver.findElement(By.id("Mobile")).getText());// Este da error
            assertEquals(actual.get("email"), driver.findElement(By.id("Email")).getText());
            assertEquals(actual.get("something"), driver.findElement(By.id("About")).getText());// Este da error
            assertEquals(actual.get("personalsite"), driver.findElement(By.id("Url")).getText());
        }

    }

    @Test
    void logOutTest() {
        driver.findElement(By.id("logoutBTN")).click();
        assertDoesNotThrow(() -> {
            wait.until(ExpectedConditions.urlToBe("https://testappautomation.herokuapp.com/"));
        });
        assertThrows(org.openqa.selenium.NoSuchElementException.class, () -> {
            driver.findElement(By.id("logoutBTN"));
        });
        assertThrows(org.openqa.selenium.NoSuchElementException.class, () -> {
            driver.findElement(By.id("profileIMG"));
        });
        assertThrows(org.openqa.selenium.NoSuchElementException.class, () -> {
            driver.findElement(By.id("profileTXT"));
        });
        assertDoesNotThrow(() -> {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginBTN")));
        });
    }

    ArrayList<HashMap<String, String>> createControls() {
        ArrayList<HashMap<String, String>> lista = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> aux;

        aux = new HashMap<String, String>();
        aux.put("nombre", "Nicolas");
        aux.put("apellido", "Frankenfeld");
        aux.put("apodo", "Nico");
        aux.put("email", "nicolas@gmail.com");
        aux.put("personalsite", "nicolas.com.ar");
        aux.put("mobileNumber", "0303456");
        aux.put("title", "Dr");
        aux.put("something", "Este es un texto valido");
        aux.put("?", "Si");
        lista.add(aux);

        return lista;
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}
