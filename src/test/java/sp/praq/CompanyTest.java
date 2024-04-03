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
        Assertions.assertEquals(c.get(0).getId(), 1);
        Assertions.assertEquals(c.get(1).getId(), 2);
    }
}
