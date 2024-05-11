package sp.praq;

import org.junit.Assert;
import sp.praq.models.*;
import sp.praq.services.*;
import org.junit.jupiter.api.*;
import java.util.*;
import java.time.*;

public class StudentTest {
    @Test
    public void testStudent() {
        Student s = new Student("Пушкин", "Александр", "Сергеевич",
                "pushkin_test@mail.ru", "+71234567890");
        Assertions.assertEquals(s.getSurname(), "Пушкин");
        Assertions.assertEquals(s.getName(), "Александр");
        Assertions.assertEquals(s.getPatronymic(), "Сергеевич");
        Assertions.assertEquals(s.getEmail(), "pushkin_test@mail.ru");
        Assertions.assertEquals(s.getPhone_number(), "+71234567890");
    }
    @Test
    public void testFindById() {
        StudentService service = new StudentService();
        Student s = service.findById(1);
        Assertions.assertEquals(s.getId(), 1);
        // ('Иванов', 'Петр', 'Иванович', 'ivanov@mail.ru', '+74951234567')
        Assertions.assertEquals(s.getSurname(), "Иванов");
        Assertions.assertEquals(s.getName(), "Петр");
        Assertions.assertEquals(s.getPatronymic(), "Иванович");
        Assertions.assertEquals(s.getEmail(), "ivanov@mail.ru");
        Assertions.assertEquals(s.getPhone_number(), "+74951234567");
    }

    @Test
    public void testFindAll() {
        StudentService service = new StudentService();
        List<Student> s = service.findAll();

        Assertions.assertEquals(s.size(), 2);
    }

    @Test
    public void testSaveUpdateDelete() {
        StudentService service = new StudentService();
        Student s = new Student("Пушкин", "Александр", "Сергеевич",
                "pushkin_test@mail.ru", "+71234567890");

        service.save(s);
        Student found = service.findById(s.getId());
        Assertions.assertEquals(s, found);

        s.setName("Сергей");
        service.update(s);
        found = service.findById(s.getId());
        Assertions.assertEquals(s.getName(), found.getName());

        service.delete(s);
        found = service.findById(s.getId());
        Assertions.assertEquals(null, found);
    }

    @Test
    public void testDeleteById() {
        StudentService service = new StudentService();
        Student s = new Student("Пушкин", "Александр", "Сергеевич",
                "pushkin_test@mail.ru", "+71234567890");

        service.save(s);
        Student found = service.findById(s.getId());
        Assertions.assertEquals(s, found);

        service.deleteById(s.getId());
        found = service.findById(s.getId());
        Assertions.assertEquals(null, found);
    }

    @Test
    public void testSearch() {
        StudentService service = new StudentService();
        // ('Иванов', 'Петр', 'Иванович', 'ivanov@mail.ru', '+74951234567'),
        Student newStudent = new Student("Иванов", "Иван", "Иванович", "-", "-");
        service.save(newStudent);

        List<Student> ls = service.search("Ивано", null, null);
        Assertions.assertEquals(ls.size(), 2);
        Assertions.assertEquals(ls.get(0).getName(), "Петр");
        Assertions.assertEquals(ls.get(1).getName(), "Иван");

        service.delete(newStudent);
        ls = service.search("Иванов", "Петр", "Иванович");
        Assertions.assertEquals(ls.size(), 1);
        Assertions.assertEquals(ls.get(0), service.findById(1));

        ls = service.search("Пушкин", null, null);
        Assertions.assertEquals(ls.size(), 0);
    }

    @Test
    public void testGetSchedule() {
        StudentService service = new StudentService();
        LocalDateTime start = LocalDateTime.of(2024, 4, 1, 0, 0, 0);
        LocalDateTime end = LocalDateTime.of(2024, 4, 4, 0, 0, 0);
        List<Lesson> ll = service.getSchedule(service.findById(1), start, end);
        Assertions.assertEquals(ll.size(), 3);
        Student s = new Student("Пушкин", "Александр", "Сергеевич",
                "pushkin_test@mail.ru", "+71234567890");
        service.save(s);
        ll = service.getSchedule(s, start, end);
        Assertions.assertEquals(ll.size(), 0);
        service.delete(s);
    }
}
