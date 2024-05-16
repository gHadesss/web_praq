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
        driver.get("localhost:8080/");
        btn = driver.findElement(By.id("students"));
        btn.click();
        assertEquals("Студенты", driver.getTitle());
        driver.get("localhost:8080/");
        btn = driver.findElement(By.id("groups"));
        btn.click();
        assertEquals("Группы", driver.getTitle());
        driver.quit();
    }

    @Test
    public void testCompanies() {
        driver.get("http://localhost:8080");
        driver.manage().window().maximize();
        WebElement btn = driver.findElement(By.id("companies"));
        btn.click();
        assertEquals("Компании", driver.getTitle());
        btn = driver.findElement(By.linkText("Вернуться назад"));
        btn.click();
        assertEquals("Тренинговый центр", driver.getTitle());

        driver.get("http://localhost:8080/companies");
        WebElement table = driver.findElement(By.tagName("table"));
        List<WebElement> companies = table.findElements(By.tagName("td"));

        assertEquals("ОАО \"Курсы\"", companies.get(0).getText());
        assertEquals("ОАО \"Яндекс\"", companies.get(1).getText());

        companies.get(0).findElements(By.tagName("a")).get(0).click();
        assertEquals("Информация о компании", driver.getTitle());
        WebElement header = driver.findElement(By.tagName("h1"));
        List<WebElement> content = header.findElements(By.tagName("span"));
        assertEquals("ОАО \"Курсы\"", content.get(0).getText());
        List<WebElement> address = driver.findElements(By.id("address"));
        assertEquals("г. Москва, ул. Пушкина, д. 9", address.get(0).getText());
        table = driver.findElement(By.id("course"));
        content = table.findElements(By.tagName("td"));
        assertEquals("Курс 2", content.get(0).getText());
        assertEquals("10.0", content.get(1).getText());
        content.get(0).findElements(By.tagName("a")).get(0).click();
        assertEquals("Информация о курсе", driver.getTitle());
        content = driver.findElements(By.tagName("h1"));
        assertEquals("Курс 2", content.get(0).getText());
        btn = driver.findElement(By.linkText("Вернуться назад"));
        btn.click();
        assertEquals("Информация о компании", driver.getTitle());
        btn = driver.findElement(By.linkText("Вернуться назад"));
        btn.click();
        assertEquals("Компании", driver.getTitle());
        driver.quit();
    }

    @Test
    public void testEditAndDeleteCompany() {
        driver.get("http://localhost:8080/companies");
        driver.manage().window().maximize();
        WebElement btn = driver.findElement(By.className("btn-primary"));
        btn.click();
        assertEquals("Добавить компанию", driver.getTitle());
        btn = driver.findElement(By.linkText("Вернуться назад"));
        btn.click();
        assertEquals("Компании", driver.getTitle());
        btn = driver.findElement(By.className("btn-primary"));
        btn.click();
        assertEquals("Добавить компанию", driver.getTitle());
        String name = "Test company", city = "г. Москва", street = "территория Ленинские горы", house = "д. 1с52";
        WebElement field = driver.findElement(By.id("name"));
        field.sendKeys(name);
        field = driver.findElement(By.id("city"));
        field.sendKeys(city);
        field = driver.findElement(By.id("street"));
        field.sendKeys(street);
        field = driver.findElement(By.id("house"));
        field.sendKeys(house);

        WebElement form = driver.findElement(By.tagName("form"));
        btn = form.findElement(By.tagName("button"));
        btn.click();
        assertEquals("Компании", driver.getTitle());

        WebElement table = driver.findElement(By.tagName("table"));
        List<WebElement> companies = table.findElements(By.tagName("td"));

        assertEquals(name, companies.get(2).getText());
        companies.get(2).findElements(By.tagName("a")).get(0).click();
        assertEquals("Информация о компании", driver.getTitle());

        List<WebElement> address = driver.findElements(By.id("address"));
        assertEquals(city + ", " + street + ", " + house, address.get(0).getText());

        btn = driver.findElement(By.xpath("//button[contains(text(),'Редактировать')]"));
        btn.click();

        field = driver.findElement(By.id("title"));
        field.clear();
        name = "Test company 2";
        field.sendKeys(name);
        btn = driver.findElement(By.xpath("//button[contains(text(),'Сохранить')]"));
        btn.click();

        table = driver.findElement(By.tagName("table"));
        companies = table.findElements(By.tagName("td"));
        assertEquals(name, companies.get(2).getText());
        companies.get(2).findElements(By.tagName("a")).get(0).click();

        btn = driver.findElement(By.xpath("//button[contains(text(),'Удалить')]"));
        btn.click();
        assertEquals("Компании", driver.getTitle());
        table = driver.findElement(By.tagName("table"));
        companies = table.findElements(By.tagName("td"));

        assertEquals(2, companies.size());

        driver.quit();
    }

    @Test
    public void testCourses() {
        driver.get("http://localhost:8080");
        driver.manage().window().maximize();
        WebElement btn = driver.findElement(By.id("courses"));
        btn.click();
        assertEquals("Курсы", driver.getTitle());
        btn = driver.findElement(By.linkText("Вернуться назад"));
        btn.click();
        assertEquals("Тренинговый центр", driver.getTitle());

        driver.get("http://localhost:8080/courses");
        WebElement table = driver.findElement(By.tagName("table"));
        List<WebElement> courses = table.findElements(By.tagName("td"));

        assertEquals("Курс 1", courses.get(0).getText());
        assertEquals("Курс 2", courses.get(2).getText());

        assertEquals("ОАО \"Яндекс\"", courses.get(1).getText());
        assertEquals("ОАО \"Курсы\"", courses.get(3).getText());

        btn = driver.findElement(By.xpath("//button[contains(text(),'по компании')]"));
        btn.click();

        table = driver.findElement(By.tagName("table"));
        courses = table.findElements(By.tagName("td"));
        assertEquals("Курс 1", courses.get(2).getText());
        assertEquals("Курс 2", courses.get(0).getText());

        assertEquals("ОАО \"Яндекс\"", courses.get(3).getText());
        assertEquals("ОАО \"Курсы\"", courses.get(1).getText());

        btn = driver.findElement(By.xpath("//button[contains(text(),'по курсу')]"));
        btn.click();

        table = driver.findElement(By.tagName("table"));
        courses = table.findElements(By.tagName("td"));
        assertEquals("Курс 1", courses.get(0).getText());
        assertEquals("Курс 2", courses.get(2).getText());

        assertEquals("ОАО \"Яндекс\"", courses.get(1).getText());
        assertEquals("ОАО \"Курсы\"", courses.get(3).getText());

        courses.get(0).findElements(By.tagName("a")).get(0).click();
        assertEquals("Информация о курсе", driver.getTitle());
        List<WebElement> tables = driver.findElements(By.tagName("table"));
        assertEquals("ОАО \"Яндекс\"", tables.get(0).findElements(By.tagName("td")).get(1).getText());

        tables.get(0).findElements(By.tagName("a")).get(0).click();
        assertEquals("Информация о компании", driver.getTitle());

        WebElement title = driver.findElement(By.tagName("h1"));
        assertEquals("ОАО \"Яндекс\"", title.getText());
        btn = driver.findElement(By.linkText("Вернуться назад"));
        btn.click();

        tables = driver.findElements(By.tagName("table"));
        assertEquals("Иванов Иван Иванович", tables.get(1).findElements(By.tagName("td")).get(0).getText());
        tables.get(1).findElements(By.tagName("a")).get(0).click();
        assertEquals("Информация о преподавателе", driver.getTitle());
        title = driver.findElement(By.tagName("h1"));
        assertEquals("Иванов Иван Иванович", title.getText());

        btn = driver.findElement(By.linkText("Вернуться назад"));
        btn.click();
        tables = driver.findElements(By.tagName("table"));
        assertEquals("ОАО \"Курсы\"", tables.get(1).findElements(By.tagName("td")).get(1).getText());
        tables.get(1).findElements(By.tagName("a")).get(1).click();
        assertEquals("Информация о компании", driver.getTitle());
        title = driver.findElement(By.tagName("h1"));
        assertEquals("ОАО \"Курсы\"", title.getText());

        driver.quit();
    }

    @Test
    public void testCreateAndDeleteTutor() {
        driver.get("http://localhost:8080/courses");
        driver.manage().window().maximize();
        assertEquals("Курсы", driver.getTitle());
        WebElement btn = driver.findElement(By.xpath("//button[contains(text(),'Добавить курс')]"));
        btn.click();

        assertEquals("Добавить курс", driver.getTitle());




        driver.quit();
    }
}
