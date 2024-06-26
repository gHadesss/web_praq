package sp.praq;

import sp.praq.models.*;
import sp.praq.services.*;
import org.junit.jupiter.api.*;
import java.util.*;

public class CompanyTest {
    @Test
    public void testCompany() {
        Company company = new Company("Test Company", new Address("New York", "Wall St.", "100500"));
        Assertions.assertEquals(company.getTitle(), "Test Company");
        Assertions.assertEquals(company.getAddress(), new Address("New York", "Wall St.", "100500"));
    }

    @Test
    public void testFindById() {
        try {
            CompanyService cs = new CompanyService();
            Company c = cs.findById(1);
            Assertions.assertEquals(c.getId(), 1);
            Assertions.assertEquals(c.getTitle(), "ОАО \"Курсы\"");
            Assertions.assertEquals(c.getAddress(), new Address("г. Москва", "ул. Пушкина", "д. 9"));
        } catch (Exception e) {
            Assertions.assertEquals(0, 1);
        }
    }

    @Test
    public void testFindAll() {
        CompanyService cs = new CompanyService();
        List<Company> c = cs.findAll();

        Assertions.assertEquals(c.size(), 2);
    }

    @Test
    public void testSaveUpdateDelete() {
        try {
            CompanyService cs = new CompanyService();
            Company c = new Company("Test Company", new Address("New York", "Wall St.", "100500"));

            cs.save(c);
            Company found = cs.findById(c.getId());
            Assertions.assertEquals(c, found);

            c.setTitle("New Title");
            cs.update(c);
            found = cs.findById(c.getId());
            Assertions.assertEquals(c.getTitle(), found.getTitle());

            cs.delete(c);
            found = cs.findById(c.getId());
//            Assertions.assertEquals(null, found);
        } catch (Exception e) {
            Assertions.assertEquals("java.lang.Exception: Нет Company с таким id.", e.toString());
        }
    }

    @Test
    public void testDeleteById() {
        try {
            CompanyService cs = new CompanyService();
            Company c = new Company("Test Company", new Address("New York", "Wall St.", "100500"));

            cs.save(c);
            Company found = cs.findById(c.getId());
            Assertions.assertEquals(c, found);

            cs.deleteById(c.getId());
            found = cs.findById(c.getId());
//            Assertions.assertEquals(null, found);
        } catch (Exception e) {
            Assertions.assertEquals("java.lang.Exception: Нет Company с таким id.", e.toString());
        }
    }
}
