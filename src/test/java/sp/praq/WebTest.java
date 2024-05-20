package sp.praq;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class WebTest {
    WebDriver driver = new ChromeDriver();

    // ссылки в шапках страниц, протестированы из /index.html, но там статические ссылки
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

    // тесты кликов по всем ссылкам из раздела с компаниями
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

    // тестирование добавления компании, проверка появления ссылок, удаления компании
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

    // тесты кликов по всем ссылкам из раздела с компаниями
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

    // тестирование добавления курса, проверка появления ссылок, удаления курса
    @Test
    public void testCreateAndDeleteCourse() {
        driver.get("http://localhost:8080/courses");
        driver.manage().window().maximize();
        assertEquals("Курсы", driver.getTitle());
        WebElement btn = driver.findElement(By.xpath("//button[contains(text(),'Добавить курс')]"));
        btn.click();
        assertEquals("Добавить курс", driver.getTitle());

        String title = "Test course", totalHours = "5.0", description = "Test course description.";
        WebElement field = driver.findElement(By.id("title"));
        field.sendKeys(title);
        field = driver.findElement(By.id("totalHours"));
        field.sendKeys(totalHours);
        field = driver.findElement(By.id("description"));
        field.sendKeys(description);

        btn = driver.findElement(By.xpath("//button[contains(text(),'Сохранить')]"));
        btn.click();
        btn = driver.findElement(By.xpath("//button[contains(text(), 'Ok')]"));
        btn.click();

        btn = driver.findElement(By.id("companyDropdown"));
        btn.click();
        btn = driver.findElement(By.xpath("//a[@class='dropdown-item company-item' and text()='ОАО \"Яндекс\"']"));
        btn.click();
        btn = driver.findElement(By.xpath("//button[contains(text(),'Сохранить')]"));
        btn.click();


        WebElement table = driver.findElement(By.tagName("table"));
        List<WebElement> courses = table.findElements(By.tagName("td"));
        assertEquals(6, courses.size());

        assertEquals(title, courses.get(0).getText());
        assertEquals("ОАО \"Яндекс\"", courses.get(1).getText());
        courses.get(0).findElements(By.tagName("a")).get(0).click();
        btn = driver.findElement(By.xpath("//button[contains(text(),'Редактировать')]"));
        btn.click();

        title = "Test course 2";
        field = driver.findElement(By.id("title"));
        field.clear();
        field.sendKeys(title);

        btn = driver.findElement(By.xpath("//button[contains(text(),'Сохранить')]"));
        btn.click();
        btn = driver.findElement(By.xpath("//button[contains(text(), 'Ok')]"));
        btn.click();

        btn = driver.findElement(By.id("companyDropdown"));
        btn.click();
        btn = driver.findElement(By.xpath("//a[@class='dropdown-item company-item' and text()='ОАО \"Яндекс\"']"));
        btn.click();
        btn = driver.findElement(By.xpath("//button[contains(text(),'Сохранить')]"));
        btn.click();

        btn = driver.findElement(By.linkText("Курсы"));
        btn.click();
        table = driver.findElement(By.tagName("table"));
        courses = table.findElements(By.tagName("td"));
        assertEquals(title, courses.get(0).getText());
        courses.get(0).findElements(By.tagName("a")).get(0).click();
        btn = driver.findElement(By.xpath("//button[contains(text(),'Удалить')]"));
        btn.click();

        table = driver.findElement(By.tagName("table"));
        courses = table.findElements(By.tagName("td"));
        assertEquals(4, courses.size());
        driver.quit();
    }

    // тесты кликов по всем ссылкам из раздела с преподавателями, проверка расписания
    @Test
    public void testTutors() {
        driver.get("http://localhost:8080");
        driver.manage().window().maximize();
        WebElement btn = driver.findElement(By.id("tutors"));
        btn.click();
        assertEquals("Преподаватели", driver.getTitle());
        btn = driver.findElement(By.linkText("Вернуться назад"));
        btn.click();
        assertEquals("Тренинговый центр", driver.getTitle());

        driver.get("http://localhost:8080/tutors");
        WebElement table = driver.findElement(By.tagName("table"));
        List<WebElement> tutors = table.findElements(By.tagName("td"));

        assertEquals("Баширов Артур Вадимович", tutors.get(0).getText());
        assertEquals("Иванов Иван Иванович", tutors.get(2).getText());

        assertEquals("ОАО \"Яндекс\"", tutors.get(1).getText());
        assertEquals("ОАО \"Курсы\"", tutors.get(3).getText());

        btn = driver.findElement(By.xpath("//button[contains(text(),'по компании')]"));
        btn.click();

        table = driver.findElement(By.tagName("table"));
        tutors = table.findElements(By.tagName("td"));

        assertEquals("Баширов Артур Вадимович", tutors.get(2).getText());
        assertEquals("Иванов Иван Иванович", tutors.get(0).getText());

        assertEquals("ОАО \"Яндекс\"", tutors.get(3).getText());
        assertEquals("ОАО \"Курсы\"", tutors.get(1).getText());

        btn = driver.findElement(By.xpath("//button[contains(text(),'по ФИО')]"));
        btn.click();

        table = driver.findElement(By.tagName("table"));
        tutors = table.findElements(By.tagName("td"));

        assertEquals("Баширов Артур Вадимович", tutors.get(0).getText());
        assertEquals("Иванов Иван Иванович", tutors.get(2).getText());

        assertEquals("ОАО \"Яндекс\"", tutors.get(1).getText());
        assertEquals("ОАО \"Курсы\"", tutors.get(3).getText());

        tutors.get(0).findElements(By.tagName("a")).get(0).click();
        assertEquals("Информация о преподавателе", driver.getTitle());
        WebElement header = driver.findElement(By.tagName("h1"));
        assertEquals("Баширов Артур Вадимович", header.getText());
        List<WebElement> tables = driver.findElements(By.tagName("table"));
        assertEquals("ОАО \"Яндекс\"", tables.get(0).findElements(By.tagName("td")).get(1).getText());

        tables.get(0).findElements(By.tagName("a")).get(0).click();
        assertEquals("Информация о компании", driver.getTitle());

        WebElement title = driver.findElement(By.tagName("h1"));
        assertEquals("ОАО \"Яндекс\"", title.getText());
        btn = driver.findElement(By.linkText("Вернуться назад"));
        btn.click();

        tables = driver.findElements(By.tagName("table"));
        assertEquals("Курс 2", tables.get(1).findElements(By.tagName("td")).get(0).getText());
        tables.get(1).findElements(By.tagName("a")).get(0).click();
        assertEquals("Информация о курсе", driver.getTitle());
        title = driver.findElement(By.tagName("h1"));
        assertEquals("Курс 2", title.getText());

        btn = driver.findElement(By.linkText("Вернуться назад"));
        btn.click();
        tables = driver.findElements(By.tagName("table"));
        assertEquals("ОАО \"Курсы\"", tables.get(1).findElements(By.tagName("td")).get(1).getText());
        tables.get(1).findElements(By.tagName("a")).get(1).click();
        assertEquals("Информация о компании", driver.getTitle());
        title = driver.findElement(By.tagName("h1"));
        assertEquals("ОАО \"Курсы\"", title.getText());
        btn = driver.findElement(By.linkText("Вернуться назад"));
        btn.click();

        WebElement date = driver.findElement(By.id("startDate"));
        date.sendKeys("01012024");
        date = driver.findElement(By.id("endDate"));
        date.sendKeys("01012023");
        btn = driver.findElement(By.xpath("//button[contains(text(),'Получить расписание')]"));
        btn.click();

        assertEquals("Ошибка", driver.getTitle());
        btn = driver.findElement(By.linkText("Вернуться назад"));
        btn.click();

        date = driver.findElement(By.id("startDate"));
        date.sendKeys("01012024");
        date = driver.findElement(By.id("endDate"));
        date.sendKeys("01012025");
        btn = driver.findElement(By.xpath("//button[contains(text(),'Получить расписание')]"));
        btn.click();

        assertEquals("Расписание", driver.getTitle());
        header = driver.findElement(By.tagName("h1"));
        assertEquals("Расписание преподавателя: Баширов Артур Вадимович", header.getText());
        table = driver.findElement(By.tagName("tbody"));
        List<WebElement> lessons = table.findElements(By.tagName("tr"));
        assertEquals(3, lessons.size());
        WebElement row = lessons.get(1);
        assertEquals("02.04.2024, 15:00", row.findElements(By.tagName("td")).get(0).getText());
        assertEquals("02.04.2024, 16:00", row.findElements(By.tagName("td")).get(1).getText());
        assertEquals("1", row.findElements(By.tagName("td")).get(2).getText());
        assertEquals("1", row.findElements(By.tagName("td")).get(3).getText());
        assertEquals("Курс 2", row.findElements(By.tagName("td")).get(4).getText());

        row.findElements(By.tagName("td")).get(3).findElement(By.tagName("a")).click();
        assertEquals("Информация о группе", driver.getTitle());
        header = driver.findElement(By.tagName("h1"));
        assertEquals("Группа 1", header.getText());
        driver.findElement(By.linkText("Вернуться назад")).click();

        table = driver.findElement(By.tagName("tbody"));
        lessons = table.findElements(By.tagName("tr"));
        row = lessons.get(1);
        row.findElements(By.tagName("td")).get(4).findElement(By.tagName("a")).click();
        assertEquals("Информация о курсе", driver.getTitle());
        header = driver.findElement(By.tagName("h1"));
        assertEquals("Курс 2", header.getText());
        driver.findElement(By.linkText("Вернуться назад")).click();

        btn = driver.findElement(By.xpath("//button[contains(text(), 'Добавить занятие')]"));
        btn.click();

        assertEquals("Назначить занятие", driver.getTitle());
        WebElement field = driver.findElement(By.id("datetime"));
        field.sendKeys("20050020241000");
        field = driver.findElement(By.id("room"));
        field.sendKeys("615");
//        field.sendKeys(Keys.ENTER);
        field = driver.findElement(By.id("duration"));
        field.sendKeys("1.5");
//        field.sendKeys(Keys.ENTER);

        btn = driver.findElement(By.xpath("//button[contains(text(), 'Сохранить')]"));
        btn.click();

        btn = driver.findElement(By.xpath("//button[contains(text(), 'Ok')]"));
        btn.click();

        btn = driver.findElement(By.id("groupDropdown"));
        btn.click();
        btn = driver.findElement(By.xpath("//a[@class='dropdown-item group-item' and text()='1']"));
        btn.click();
        btn = driver.findElement(By.xpath("//button[contains(text(), 'Сохранить')]"));
        btn.click();

        assertEquals("Расписание", driver.getTitle());
        table = driver.findElement(By.tagName("tbody"));
        lessons = table.findElements(By.tagName("tr"));
        assertEquals(4, lessons.size());
        row = lessons.get(3);
        assertEquals("20.05.2024, 11:30", row.findElements(By.tagName("td")).get(1).getText());
        btn = row.findElements(By.tagName("td")).get(5);
        btn.findElement(By.tagName("button")).click();
        assertEquals("Расписание", driver.getTitle());
        table = driver.findElement(By.tagName("tbody"));
        lessons = table.findElements(By.tagName("tr"));
        assertEquals(3, lessons.size());
        driver.quit();
    }

//     тестирование добавления преподавателя, проверка появления ссылок, удаления преподавателя
    @Test
    public void testCreateAndDeleteTutor() {
        driver.get("http://localhost:8080/tutors");
        driver.manage().window().maximize();
        assertEquals("Преподаватели", driver.getTitle());
        WebElement btn = driver.findElement(By.xpath("//button[contains(text(),'Добавить преподавателя')]"));
        btn.click();
        assertEquals("Добавить преподавателя", driver.getTitle());

        String surname = "Пушкин", name = "Александр", patronymic = "Сергеевич", email = "test@mail.ru", pn = "+71234567890", bio = "Русский поэт, драматург и прозаик.";
        WebElement field = driver.findElement(By.id("surname"));
        field.sendKeys(surname);
        field = driver.findElement(By.id("name"));
        field.sendKeys(name);
        field = driver.findElement(By.id("patronymic"));
        field.sendKeys(patronymic);
        field = driver.findElement(By.id("email"));
        field.sendKeys(email);
        field = driver.findElement(By.id("phoneNumber"));
        field.sendKeys(pn);
        field = driver.findElement(By.id("description"));
        field.sendKeys(bio);

        btn = driver.findElement(By.xpath("//button[contains(text(),'Сохранить')]"));
        btn.click();
        btn = driver.findElement(By.xpath("//button[contains(text(), 'Ok')]"));
        btn.click();

        btn = driver.findElement(By.id("companyDropdown"));
        btn.click();
        btn = driver.findElement(By.xpath("//a[@class='dropdown-item company-item' and text()='ОАО \"Яндекс\"']"));
        btn.click();
        btn = driver.findElement(By.xpath("//button[contains(text(),'Сохранить')]"));
        btn.click();

        WebElement table = driver.findElement(By.tagName("tbody"));
        List<WebElement> tutors = table.findElements(By.tagName("td"));
        assertEquals(6, tutors.size());
        btn = driver.findElement(By.xpath("//button[contains(text(),'по компании')]"));
        btn.click();


        table = driver.findElement(By.tagName("tbody"));
        tutors = table.findElements(By.tagName("td"));
        assertEquals("Пушкин Александр Сергеевич", tutors.get(4).getText());
        assertEquals("ОАО \"Яндекс\"", tutors.get(5).getText());

        tutors.get(4).findElement(By.tagName("a")).click();
        assertEquals("Информация о преподавателе", driver.getTitle());
        WebElement header = driver.findElement(By.tagName("h1"));
        assertEquals("Пушкин Александр Сергеевич", header.getText());

        WebElement date = driver.findElement(By.id("startDate"));
        date.sendKeys("01012024");
        date = driver.findElement(By.id("endDate"));
        date.sendKeys("01012025");
        btn = driver.findElement(By.xpath("//button[contains(text(),'Получить расписание')]"));
        btn.click();

        assertEquals("Расписание", driver.getTitle());
        header = driver.findElement(By.tagName("h2"));
        assertEquals("На заданный период занятий у этого преподавателя не назначено", header.getText());
        driver.findElement(By.linkText("Вернуться назад")).click();

        btn = driver.findElement(By.xpath("//button[contains(text(),'Редактировать')]"));
        btn.click();

        surname = "Колотушкин";
        field = driver.findElement(By.id("surname"));
        field.clear();
        field.sendKeys(surname);

        btn = driver.findElement(By.xpath("//button[contains(text(),'Сохранить')]"));
        btn.click();
        btn = driver.findElement(By.xpath("//button[contains(text(), 'Ok')]"));
        btn.click();

        btn = driver.findElement(By.id("companyDropdown"));
        btn.click();
        btn = driver.findElement(By.xpath("//a[@class='dropdown-item company-item' and text()='ОАО \"Яндекс\"']"));
        btn.click();
        btn = driver.findElement(By.xpath("//button[contains(text(),'Сохранить')]"));
        btn.click();

        btn = driver.findElement(By.linkText("Преподаватели"));
        btn.click();
        driver.findElement(By.xpath("//button[contains(text(),'по компании')]")).click();

        table = driver.findElement(By.tagName("tbody"));
        tutors = table.findElements(By.tagName("td"));
        assertEquals("Колотушкин Александр Сергеевич", tutors.get(4).getText());
        tutors.get(4).findElements(By.tagName("a")).get(0).click();
        btn = driver.findElement(By.xpath("//button[contains(text(),'Удалить')]"));
        btn.click();

        table = driver.findElement(By.tagName("tbody"));
        tutors = table.findElements(By.tagName("td"));
        assertEquals(4, tutors.size());
        driver.quit();
    }

    // тест страницы с группами
    @Test
    public void testGroups() {
        driver.get("http://localhost:8080");
        driver.manage().window().maximize();
        WebElement btn = driver.findElement(By.id("groups"));
        btn.click();
        assertEquals("Группы", driver.getTitle());
        btn = driver.findElement(By.linkText("Вернуться назад"));
        btn.click();
        assertEquals("Тренинговый центр", driver.getTitle());

        driver.get("http://localhost:8080/groups");
        WebElement table = driver.findElement(By.tagName("table"));
        List<WebElement> groups = table.findElements(By.tagName("td"));
        assertEquals(6, groups.size());

        assertEquals("1", groups.get(0).getText());
        assertEquals("2", groups.get(3).getText());

        assertEquals("Курс 2", groups.get(1).getText());
        assertEquals("Курс 1", groups.get(4).getText());

        assertEquals("Баширов Артур Вадимович", groups.get(2).getText());
        assertEquals("Иванов Иван Иванович", groups.get(5).getText());

        groups.get(0).findElement(By.tagName("a")).click();
        assertEquals("Информация о группе", driver.getTitle());
        assertEquals("Группа 1", driver.findElement(By.tagName("h1")).getText());
        table = driver.findElements(By.tagName("tbody")).get(1);
        List<WebElement> students = table.findElements(By.tagName("td"));
        assertEquals(4, students.size());

        driver.findElement(By.xpath("//button[contains(text(),'Добавить студента в группу')]")).click();
        assertEquals("Добавить студента в группу", driver.getTitle());
        assertEquals("Нельзя добавить никакого студента в группу!", driver.findElement(By.tagName("h1")).getText());
        driver.findElement(By.linkText("Вернуться назад")).click();
        assertEquals("Информация о группе", driver.getTitle());
        table = driver.findElements(By.tagName("tbody")).get(1);
        students = table.findElements(By.tagName("td"));
        students.get(0).findElement(By.tagName("a")).click();
        assertEquals("Информация о студенте", driver.getTitle());
        assertEquals("Иванов Петр Иванович", driver.findElement(By.tagName("h1")).getText());
        driver.findElement(By.linkText("Вернуться назад")).click();

        table = driver.findElements(By.tagName("tbody")).get(0);
        assertEquals("Курс 2", table.findElements(By.tagName("td")).get(1).getText());
        assertEquals("Баширов Артур Вадимович", table.findElements(By.tagName("td")).get(3).getText());

        table.findElements(By.tagName("td")).get(1).findElement(By.tagName("a")).click();
        assertEquals("Информация о курсе", driver.getTitle());
        assertEquals("Курс 2", driver.findElement(By.tagName("h1")).getText());
        driver.findElement(By.linkText("Вернуться назад")).click();

        table = driver.findElements(By.tagName("tbody")).get(0);
        table.findElements(By.tagName("td")).get(3).findElement(By.tagName("a")).click();
        assertEquals("Информация о преподавателе", driver.getTitle());
        assertEquals("Баширов Артур Вадимович", driver.findElement(By.tagName("h1")).getText());

        driver.quit();
    }

    @Test
    public void testCreateAndDeleteGroup() {
        driver.get("http://localhost:8080/groups");
        driver.manage().window().maximize();
        assertEquals("Группы", driver.getTitle());
        WebElement table = driver.findElement(By.tagName("tbody"));
        List<WebElement> groups = table.findElements(By.tagName("td"));
        assertEquals(6, groups.size());

        WebElement btn = driver.findElement(By.xpath("//button[contains(text(),'Создать группу')]"));
        btn.click();
        assertEquals("Создать группу", driver.getTitle());

        btn = driver.findElement(By.xpath("//button[contains(text(),'Сохранить')]"));
        btn.click();
        btn = driver.findElement(By.xpath("//button[contains(text(), 'Ok')]"));
        btn.click();

        btn = driver.findElement(By.id("courseDropdown"));
        btn.click();
        btn = driver.findElement(By.xpath("//a[@class='dropdown-item course-item' and text()='Курс 2']"));
        btn.click();

        btn = driver.findElement(By.xpath("//button[contains(text(),'Сохранить')]"));
        btn.click();
        btn = driver.findElement(By.id("tutorAlert")).findElement(By.tagName("button"));
        btn.click();

        btn = driver.findElement(By.id("tutorDropdown"));
        btn.click();
        btn = driver.findElement(By.xpath("//a[@class='dropdown-item tutor-item' and text()='Иванов Иван Иванович']"));
        btn.click();

        btn = driver.findElement(By.xpath("//button[contains(text(),'Сохранить')]"));
        btn.click();

        table = driver.findElement(By.tagName("tbody"));
        groups = table.findElements(By.tagName("td"));
        assertEquals(9, groups.size());

        assertNotEquals("1", groups.get(6).getText());
        assertNotEquals("2", groups.get(6).getText());
        assertEquals("Курс 2", groups.get(7).getText());
        assertEquals("Иванов Иван Иванович", groups.get(8).getText());

        groups.get(6).findElement(By.tagName("a")).click();
        assertEquals("Информация о группе", driver.getTitle());
        assertNotEquals("Группа 1", driver.findElement(By.tagName("h1")).getText());
        assertNotEquals("Группа 2", driver.findElement(By.tagName("h1")).getText());

        table = driver.findElements(By.tagName("tbody")).get(0);
        assertEquals("Курс 2", table.findElements(By.tagName("td")).get(1).getText());
        assertEquals("Иванов Иван Иванович", table.findElements(By.tagName("td")).get(3).getText());

        table = driver.findElements(By.tagName("tbody")).get(1);
        List<WebElement> students = table.findElements(By.tagName("td"));
        assertEquals(true, students.isEmpty());
        driver.findElement(By.xpath("//button[contains(text(),'Добавить студента в группу')]")).click();
        assertEquals("Добавить студента в группу", driver.getTitle());

        driver.findElement(By.xpath("//button[contains(text(),'Сохранить')]")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Ok')]")).click();

        driver.findElement(By.id("studentDropdown")).click();
        driver.findElement(By.xpath("//a[@class='dropdown-item student-item' and text()='Иванов Петр Иванович']")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Сохранить')]")).click();
        assertEquals("Информация о группе", driver.getTitle());
        table = driver.findElements(By.tagName("tbody")).get(1);
        students = table.findElements(By.tagName("td"));
        assertEquals(2, students.size());

        String number = driver.findElement(By.tagName("h1")).getText().substring(7);
        students.get(0).findElement(By.tagName("a")).click();
        assertEquals("Информация о студенте", driver.getTitle());

        assertEquals(number, driver.findElements(By.tagName("tbody")).get(1).findElements(By.tagName("td")).get(3).getText());
        assertEquals("Курс 2", driver.findElements(By.tagName("tbody")).get(1).findElements(By.tagName("td")).get(2).getText());
        driver.findElement(By.linkText("Вернуться назад")).click();
        assertEquals("Информация о группе", driver.getTitle());

        driver.findElement(By.xpath("//button[contains(text(),'Добавить студента в группу')]")).click();
        assertEquals("Добавить студента в группу", driver.getTitle());

        driver.findElement(By.id("studentDropdown")).click();
        driver.findElement(By.xpath("//a[@class='dropdown-item student-item' and text()='Сидоров Константин Сидорович']")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Сохранить')]")).click();

        assertEquals("Информация о группе", driver.getTitle());
        table = driver.findElements(By.tagName("tbody")).get(1);
        students = table.findElements(By.tagName("td"));
        assertEquals(4, students.size());

        driver.findElement(By.xpath("//button[contains(text(),'Добавить студента в группу')]")).click();
        assertEquals("Добавить студента в группу", driver.getTitle());

        assertEquals("Нельзя добавить никакого студента в группу!",
                driver.findElement(By.tagName("h1")).getText());

        driver.findElement(By.linkText("Вернуться назад")).click();
        assertEquals("Информация о группе", driver.getTitle());
        driver.findElement(By.xpath("//button[contains(text(),'Удалить группу')]")).click();
        assertEquals("Группы", driver.getTitle());

        table = driver.findElement(By.tagName("tbody"));
        groups = table.findElements(By.tagName("td"));
        assertEquals(6, groups.size());

        driver.quit();
    }

    // тесты кликов по всем ссылкам из раздела со студентами, проверка расписания
    @Test
    public void testStudents() {
        driver.get("http://localhost:8080");
        driver.manage().window().maximize();
        WebElement btn = driver.findElement(By.id("students"));
        btn.click();
        assertEquals("Студенты", driver.getTitle());
        btn = driver.findElement(By.linkText("Вернуться назад"));
        btn.click();
        assertEquals("Тренинговый центр", driver.getTitle());

        driver.get("http://localhost:8080/students");
        WebElement table = driver.findElement(By.tagName("table"));
        List<WebElement> students = table.findElements(By.tagName("td"));

        assertEquals("Иванов Петр Иванович", students.get(0).getText());
        assertEquals("Сидоров Константин Сидорович", students.get(1).getText());

        WebElement field = driver.findElement(By.id("surname"));
        field.sendKeys("Иван");
        driver.findElement(By.xpath("//button[contains(text(),'Поиск')]")).click();

        table = driver.findElement(By.tagName("table"));
        students = table.findElements(By.tagName("td"));
        assertEquals(1, students.size());

        students.get(0).findElement(By.tagName("a")).click();
        assertEquals("Информация о студенте", driver.getTitle());

        assertEquals("Иванов Петр Иванович", driver.findElement(By.tagName("h1")).getText());
        assertEquals(2, driver.findElements(By.tagName("tbody")).get(1).findElements(By.tagName("td")).size());
        assertEquals("Курс 2", driver.findElements(By.tagName("tbody")).get(1).findElements(By.tagName("td")).get(0).getText());
        assertEquals("1", driver.findElements(By.tagName("tbody")).get(1).findElements(By.tagName("td")).get(1).getText());

        driver.findElements(By.tagName("tbody")).get(1).findElements(By.tagName("td")).get(0).findElement(By.tagName("a")).click();
        assertEquals("Курс 2", driver.findElement(By.tagName("h1")).getText());
        driver.findElement(By.linkText("Вернуться назад")).click();
        driver.findElements(By.tagName("tbody")).get(1).findElements(By.tagName("td")).get(1).findElement(By.tagName("a")).click();
        assertEquals("Группа 1", driver.findElement(By.tagName("h1")).getText());
        driver.findElement(By.linkText("Вернуться назад")).click();

        field = driver.findElement(By.id("startDate"));
        field.sendKeys("01012024");
        field = driver.findElement(By.id("endDate"));
        field.sendKeys("01012025");
        driver.findElement(By.xpath("//button[contains(text(),'Получить расписание')]")).click();

        assertEquals("Расписание студента: Иванов Петр Иванович", driver.findElement(By.tagName("h1")).getText());
        assertEquals(3, driver.findElement(By.tagName("tbody")).findElements(By.tagName("tr")).size());

        driver.quit();
    }

    //     тестирование добавления студента, проверка появления ссылок, удаления студента
    @Test
    public void testCreateAndDeleteStudent() {
        driver.get("http://localhost:8080/students");
        driver.manage().window().maximize();
        assertEquals("Студенты", driver.getTitle());
        WebElement btn = driver.findElement(By.xpath("//button[contains(text(),'Добавить студента')]"));
        btn.click();
        assertEquals("Добавить студента", driver.getTitle());

        String surname = "Пушкин", name = "Александр", patronymic = "Сергеевич", email = "test@mail.ru", pn = "+71234567890";
        WebElement field = driver.findElement(By.id("surname"));
        field.sendKeys(surname);
        field = driver.findElement(By.id("name"));
        field.sendKeys(name);
        field = driver.findElement(By.id("patronymic"));
        field.sendKeys(patronymic);
        field = driver.findElement(By.id("email"));
        field.sendKeys(email);
        field = driver.findElement(By.id("phoneNumber"));
        field.sendKeys(pn);

        btn = driver.findElement(By.xpath("//button[contains(text(),'Сохранить')]"));
        btn.click();

        WebElement table = driver.findElement(By.tagName("tbody"));
        List<WebElement> students = table.findElements(By.tagName("td"));
        assertEquals(3, students.size());

        assertEquals("Пушкин Александр Сергеевич", students.get(2).getText());

        students.get(2).findElement(By.tagName("a")).click();
        assertEquals("Информация о студенте", driver.getTitle());
        WebElement header = driver.findElement(By.tagName("h1"));
        assertEquals("Пушкин Александр Сергеевич", header.getText());

        WebElement date = driver.findElement(By.id("startDate"));
        date.sendKeys("01012024");
        date = driver.findElement(By.id("endDate"));
        date.sendKeys("01012025");
        btn = driver.findElement(By.xpath("//button[contains(text(),'Получить расписание')]"));
        btn.click();

        assertEquals("Расписание", driver.getTitle());
        header = driver.findElement(By.tagName("h2"));
        assertEquals("На заданный период занятий у этого студента не назначено", header.getText());
        driver.findElement(By.linkText("Вернуться назад")).click();

        btn = driver.findElement(By.xpath("//button[contains(text(),'Редактировать')]"));
        btn.click();

        surname = "Колотушкин";
        field = driver.findElement(By.id("surname"));
        field.clear();
        field.sendKeys(surname);

        btn = driver.findElement(By.xpath("//button[contains(text(),'Сохранить')]"));
        btn.click();

        btn = driver.findElement(By.linkText("Студенты"));
        btn.click();

        table = driver.findElement(By.tagName("tbody"));
        students = table.findElements(By.tagName("td"));
        assertEquals("Колотушкин Александр Сергеевич", students.get(2).getText());
        students.get(2).findElements(By.tagName("a")).get(0).click();
        btn = driver.findElement(By.xpath("//button[contains(text(),'Удалить')]"));
        btn.click();

        table = driver.findElement(By.tagName("tbody"));
        students = table.findElements(By.tagName("td"));
        assertEquals(2, students.size());
        driver.quit();
    }
}
