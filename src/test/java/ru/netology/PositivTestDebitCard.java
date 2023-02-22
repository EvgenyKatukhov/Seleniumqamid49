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

public class PositivTestDebitCard {
    private WebDriver driver;

@BeforeAll
public static void setUpAll() {
    WebDriverManager.chromedriver().setup();
}
@BeforeEach
    public void beforeEach() {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--disable-dev-shm-usage");
    options.addArguments("--no-sandbox");
    options.addArguments("--headless");
    driver = new ChromeDriver(options);
    driver.get("http://localhost:9999");
}
@AfterEach
    public void afterEach() {
    driver.quit();
    driver = null;
}

@Test
    public void shouldSendForm() {
    driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Кузнецова Анастасия");
    driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79109454927");
    driver.findElement(By.className("checkbox__box")).click();
    driver.findElement(By.className("button")).click();

    String expectedText = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
    String actualText = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();

    assertEquals(expectedText, actualText);


}


}
