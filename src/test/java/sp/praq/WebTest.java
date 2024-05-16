package sp.praq;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebTest {
    WebDriver driver = new ChromeDriver();

    @Test
    public void testMainPageAndHeader() {
        driver.get("http://localhost:8080/");
        driver.manage().window().maximize();
        assertEquals("Тренинговый центр", driver.getTitle());
        WebElement btn = driver.findElement(By.linkText("На главную"));
        btn.click();
        assertEquals("Тренинговый центр", driver.getTitle());
        btn = driver.findElement(By.id("companies"));
        btn.click();
        assertEquals("Компании", driver.getTitle());
        driver.get("localhost:8080/");
        btn = driver.findElement(By.id("courses"));
        btn.click();
        assertEquals("Курсы", driver.getTitle());
        driver.quit();
    }
}
