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
        CompanyService cs = new CompanyService();
        Company c = cs.findById(1);
        Assertions.assertEquals(c.getId(), 1);
        Assertions.assertEquals(c.getTitle(), "ООО \"Курсы\"");
        Assertions.assertEquals(c.getAddress(), new Address("г. Москва", "ул. Пушкина", "д. 9"));
    }

    @Test
    public void testFindAll() {
        CompanyService cs = new CompanyService();
        List<Company> c = cs.findAll();

        Assertions.assertEquals(c.size(), 2);
    }

    @Test
    public void testSaveUpdateDelete() {
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
        Assertions.assertEquals(null, found);
    }

    @Test
    public void testDeleteById() {
        CompanyService cs = new CompanyService();
        Company c = new Company("Test Company", new Address("New York", "Wall St.", "100500"));

        cs.save(c);
        Company found = cs.findById(c.getId());
        Assertions.assertEquals(c, found);

        cs.deleteById(c.getId());
        found = cs.findById(c.getId());
        Assertions.assertEquals(null, found);
    }
}
