package sp.praq;

import org.junit.Assert;
import sp.praq.models.*;
import sp.praq.services.*;
import org.junit.jupiter.api.*;
import java.util.*;
import java.time.*;

public class TutorTest {
    @Test
    public void testTutor() {
        Tutor t = new Tutor("Пушкин", "Александр", "Сергеевич",
                "Русский поэт, драматург и прозаик.", "pushkin_as@mail.ru", "+71234567890");
//        Assertions.assertEquals(null, t.getCompany_id());
        Assertions.assertEquals("Пушкин", t.getSurname());
        Assertions.assertEquals("Александр", t.getName());
        Assertions.assertEquals("Сергеевич", t.getPatronymic());
        Assertions.assertEquals("Русский поэт, драматург и прозаик.", t.getDescription());
        Assertions.assertEquals("pushkin_as@mail.ru", t.getEmail());
        Assertions.assertEquals("+71234567890", t.getPhone_number());
    }

    @Test
    public void testFindById() {
        try {
            TutorService ts = new TutorService();
            Tutor t = ts.findById(1);
    //        (1, 'Иванов', 'Иван', 'Иванович', 'Характер мягкий, не женат.', 'ivanov@mail.ru', '+74951234567'),
            CompanyService cs = new CompanyService();
            Assertions.assertEquals(cs.findById(1), t.getCompany_id());
            Assertions.assertEquals("Иванов", t.getSurname());
            Assertions.assertEquals("Иван", t.getName());
            Assertions.assertEquals("Иванович", t.getPatronymic());
            Assertions.assertEquals("Характер мягкий, не женат.", t.getDescription());
            Assertions.assertEquals("ivanov@mail.ru", t.getEmail());
            Assertions.assertEquals("+74951234567", t.getPhone_number());
        } catch (Exception e) {
            Assertions.assertEquals(0, 1);
        }
    }

    @Test
    public void testFindAll() {
        TutorService ts = new TutorService();
        List<Tutor> lt = ts.findAll();

        Assertions.assertEquals(2, lt.size());
    }

    @Test
    public void testSaveUpdateDelete() {
        try {
            TutorService ts = new TutorService();
            Tutor t = new Tutor("Пушкин", "Александр", "Сергеевич",
                    "Русский поэт, драматург и прозаик.", "pushkin_as@mail.ru", "+71234567890");

            ts.save(t);
            Tutor found = ts.findById(t.getId());
            Assertions.assertEquals(t, found);
            t.setDescription("Один из самых авторитетных литературных деятелей первой трети XIX века.");
            CompanyService cs = new CompanyService();
            t.setCompany_id(cs.findById(1));
            ts.update(t);
            found = ts.findById(t.getId());
            Assertions.assertEquals(t, found);
            ts.delete(t);
            found = ts.findById(t.getId());
//            Assertions.assertEquals(null, found);
        } catch (Exception e) {
            Assertions.assertEquals("java.lang.Exception: Нет Tutor с таким id.", e.toString());
        }
    }

    @Test
    public void testDeleteById() {
        try {
            TutorService ts = new TutorService();
            Tutor t = new Tutor("Пушкин", "Александр", "Сергеевич",
                    "Русский поэт, драматург и прозаик.", "pushkin_as@mail.ru", "+71234567890");

            ts.save(t);
            Tutor found = ts.findById(t.getId());
            Assertions.assertEquals(t, found);
            ts.deleteById(t.getId());
            found = ts.findById(t.getId());
//            Assertions.assertEquals(null, found);
        } catch (Exception e) {
            Assertions.assertEquals("java.lang.Exception: Нет Tutor с таким id.", e.toString());
        }
    }

    @Test
    public void testListByCompany() {
        try {
            TutorService ts = new TutorService();
            List<Tutor> lt = ts.listByCompany(null, null, null);
            Assertions.assertEquals(lt.get(0), ts.findById(1));
            Assertions.assertEquals(lt.get(1), ts.findById(2));

            lt = ts.listByCompany("Иванов", null, null);
            Assertions.assertEquals(1, lt.size());
            Assertions.assertEquals(lt.get(0), ts.findById(1));

            lt = ts.listByCompany("Иванова", null, null);
            Assertions.assertEquals(0, lt.size());

            lt = ts.listByCompany(null, "Алексей", null);
            Assertions.assertEquals(0, lt.size());

            lt = ts.listByCompany(null, null, "Михайлович");
            Assertions.assertEquals(0, lt.size());
        } catch (Exception e) {
            Assertions.assertEquals(0, 1);
        }
    }

    @Test
    public void testListByName() {
        try {
            TutorService ts = new TutorService();
            List<Tutor> lt = ts.listByName(null, null, null);
            Assertions.assertEquals(lt.get(0), ts.findById(2));
            Assertions.assertEquals(lt.get(1), ts.findById(1));

            lt = ts.listByName("Баширов", null, null);
            Assertions.assertEquals(1, lt.size());
            Assertions.assertEquals(lt.get(0), ts.findById(2));

            lt = ts.listByName("Баширова", null, null);
            Assertions.assertEquals(0, lt.size());

            lt = ts.listByCompany(null, "Алексей", null);
            Assertions.assertEquals(0, lt.size());

            lt = ts.listByCompany(null, null, "Михайлович");
            Assertions.assertEquals(0, lt.size());
        } catch (Exception e) {
            Assertions.assertEquals(0, 1);
        }
    }

    @Test
    public void testGetSchedule() {
        try {
            TutorService ts = new TutorService();
            LocalDateTime start = LocalDateTime.of(2024, 4, 1, 0, 0, 0);
            LocalDateTime end = LocalDateTime.of(2024, 4, 4, 0, 0, 0);
            List<Lesson> ll = ts.getSchedule(ts.findById(2), start, end);
            Assertions.assertEquals(ll.size(), 3);
            ll = ts.getSchedule(ts.findById(1), start, end);
            Assertions.assertEquals(ll.size(), 0);
        } catch (Exception e) {
            Assertions.assertEquals(0, 1);
        }
    }
}
