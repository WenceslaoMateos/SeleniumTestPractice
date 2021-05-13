package com.prueba;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LogInTest {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    static void setUp() {
        // Este bloque es para que no aparezca el navegador en cada prueba
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");

        // Generamos y seteamos la instancia del navegador
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 3);
    }

    @BeforeEach
    void setUpEach() {
        // Vamos a entrar a la pagina prncipal cada vez que querramos ejecutar una
        // prueba, para evitar el problema de que quede alguin logueado
        driver.get("https://testappautomation.herokuapp.com");
        // Siempre vamos a comenzar en la pagina de logueo
        driver.findElement(By.id("loginBTN")).click();
    }

    @Test
    void UserValidation1() {
        // Generamos el caso de error
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("username")).sendKeys("");
        driver.findElement(By.id("password")).sendKeys("Password");
        driver.findElement(By.name("action")).click();
        // Hacemos el sleep para darle tiempo al navegador para hacer la gestion de
        // inicio de sesion, de esta forma, cuando pasan los 3 seg, estamos seguros que
        // alguien esta logueado o no
        assertThrows(org.openqa.selenium.TimeoutException.class, () -> {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("logoutBTN")));
        });
        // Al dar click en login, tenemos que verificar que la excepcion que ocurra es
        // por no encontrar el boton de logout, de esta forma estamos seguro que no
        // loguea exitosamente
    }

    @Test
    void UserValidation2() {
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("username")).sendKeys("Username");
        driver.findElement(By.id("password")).sendKeys("");
        driver.findElement(By.name("action")).click();
        // Hacemos el sleep para darle tiempo al navegador para hacer la gestion de
        // inicio de sesion, de esta forma, cuando pasan los 3 seg, estamos seguros que
        // alguien esta logueado o no
        assertThrows(org.openqa.selenium.TimeoutException.class, () -> {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("logoutBTN")));
        });
        // Al dar click en login, tenemos que verificar que la excepcion que ocurra es
        // por no encontrar el boton de logout, de esta forma estamos seguro que no
        // loguea exitosamente
    }

    @Test
    void UserValidation3() {
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("username")).sendKeys("lelapassano@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Automation123!");
        driver.findElement(By.name("action")).click();
        // Hacemos el sleep para darle tiempo al navegador para hacer la gestion de
        // inicio de sesion, de esta forma, cuando pasan los 3 seg, estamos seguros que
        // alguien esta logueado o no
        // Hacemos click en el logout por que para casos posteriores no deberia estar
        // logueado
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("logoutBTN")));
        driver.findElement(By.id("logoutBTN")).click();
    }

    @Test
    void UserValidation4() {
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("username")).sendKeys("lelapassano@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Automation123!");
        driver.findElement(By.name("action")).click();
        // Hacemos el sleep para darle tiempo al navegador para hacer la gestion de
        // inicio de sesion, de esta forma, cuando pasan los 3 seg, estamos seguros que
        // alguien esta logueado o no
        assertDoesNotThrow(() -> {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("logoutBTN")));
        });
        // En caso que tire una excepcion, estariamos en una estado en el que no estamos
        // logueado
        // Compruebo que estemos en la pagina principal
        assertEquals(driver.getCurrentUrl(), "https://testappautomation.herokuapp.com/");
        // Al dar click en login, tenemos que verificar que la excepcion que ocurra es
        // por no encontrar el boton de logout, de esta forma estamos seguro que no
        // loguea exitosamente
        assertThrows(org.openqa.selenium.NoSuchElementException.class, () -> {
            driver.findElement(By.id("loginBTN"));
        });
        // Compruebo que exista el elemento (si no existe, tira excepcion)
        assertDoesNotThrow(() -> {
            driver.findElement(By.id("profileIMG"));
        });
        assertEquals(driver.findElement(By.id("profileTXT")).getText(), ("Welcome lelapassano!"));
        // Verifico que la imagen este a la derecha asi el texto se ve a la izquierda
        assertEquals(driver.findElement(By.id("profileIMG")).getCssValue("float"), "right");
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}
