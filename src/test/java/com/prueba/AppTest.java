package com.prueba;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

class AppTest {

    // Generamos una variable estatica para almacenar la referencia al browser
    static WebDriver driver;

    @BeforeAll
    static void setUp() {
        // Preparamos todo para que se pueda iniciar el browser a partir del WebDriver
        WebDriverManager.chromedriver().setup();
        // Generamos la instancia del browser para testear
        driver = new ChromeDriver();
    }

    @Test
    void testApp() {
        // Pedimos la pagina de google a testear
        driver.get("https://www.google.com.ar");
        // Buscamos el elemento de la barra de busqueda
        WebElement busqueda = driver.findElement(By.name("q"));
        // Escribimos en la barra de busqueda
        busqueda.sendKeys("Selenium");
        // Buscamos el boton de busqueda en la página
        WebElement boton = driver.findElement(By.name("btnK"));
        // Enviamos el formulario para buscar la cadena previamente ingresada
        boton.submit();
        // Se busca el tag link que tenga como cadena parcial "Selenium" en la pagina
        // De forma abstracta, conseguimos el primer resultado de la busqueda
        WebElement resultado = driver.findElement(By.partialLinkText("Selenium"));
        // Clickeamos el elemento obtenido
        resultado.click();
        // Usamos un assert para comprobar el titulo de la pagina
        assertEquals(driver.getTitle(), "SeleniumHQ Browser Automation");
    }

    @AfterAll
    static void tearDown() throws InterruptedException {
        // Genera una espera de 3 segundos para no cerrar el navegador al terminar
        Thread.sleep(3000);
        // Terminamos la instancia del navegadoor utilizada
        driver.quit();
    }
}
