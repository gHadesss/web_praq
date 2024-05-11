package sp.praq;

import org.junit.Assert;
import sp.praq.models.*;
import sp.praq.services.*;
import org.junit.jupiter.api.*;
import java.util.*;

public class CourseTest {
    @Test
    public void testCourse() {
        Course c = new Course("Test course title", 2.0d, "Test course description.");
        Assertions.assertEquals("Test course title", c.getTitle());
        Assertions.assertEquals(2.0, c.getTotal_hours());
        Assertions.assertEquals("Test course description.", c.getDescription());
    }

    @Test
    public void testFindById() {
        CourseService cs = new CourseService();
        Course c = new Course("Test course title", 2.0d, "Test course description.");
        cs.save(c);
        Course found = cs.findById(c.getId());
        Assertions.assertEquals(c, found);
        cs.delete(c);
        found = cs.findById(c.getId());
        Assertions.assertEquals(null, found);
    }

    @Test
    public void testSaveUpdateDelete() {
        CourseService cs = new CourseService();
        Course c = new Course("123", 2.0, "desc");

        cs.save(c);
        Course found = cs.findById(c.getId());
        Assertions.assertEquals(c, found);
        c.setDescription("Один из самых авторитетных литературных деятелей первой трети XIX века.");
        CompanyService cs1 = new CompanyService();
        c.setCompany_id(cs1.findById(1));
        cs.update(c);
        found = cs.findById(c.getId());
        Assertions.assertEquals(c, found);
        cs.delete(c);
        found = cs.findById(c.getId());
        Assertions.assertEquals(null, found);
    }

    @Test
    public void testListByCompany() {
        CourseService cs = new CourseService();
        List<Course> lc = cs.listByCompany();

        Assertions.assertEquals(2, lc.size());
        Assertions.assertEquals(cs.findById(1), lc.get(0));
        Assertions.assertEquals(cs.findById(2), lc.get(1));
    }

    @Test
    public void testListByTitle() {
        CourseService cs = new CourseService();
        List<Course> lc = cs.listByTitle();

        Assertions.assertEquals(2, lc.size());
        Assertions.assertEquals(cs.findById(2), lc.get(0));
        Assertions.assertEquals(cs.findById(1), lc.get(1));
    }

}
