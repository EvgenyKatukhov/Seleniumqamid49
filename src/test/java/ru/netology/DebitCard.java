package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DebitCard {
    private WebDriver driver;

    @BeforeAll
    public static void setUpAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    public void afterEach() {
        driver.quit();
        driver = null;
    }

    @Test // Задача 1.
    public void shouldSendForm() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Кузнецова Анастасия");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79109454927");
        driver.findElement(By.cssSelector("[data-test-id=agreement] input")).click();
        driver.findElement(By.cssSelector("button.button")).click();

        var actualText = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();

        assertEquals("Ваша зявка успешно отправлена! Наш менеджер свяжется с вами с ближайшее время.", actualText);

    }
}
