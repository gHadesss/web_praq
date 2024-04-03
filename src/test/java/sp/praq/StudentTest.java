package sp.praq;

import sp.praq.models.*;
import sp.praq.services.*;
import org.junit.jupiter.api.*;
import java.util.*;

public class StudentTest {
    @Test
    public void testFindById() {
        StudentService service = new StudentService();
        List<Student> res = service.findAll();
        Assertions.assertEquals(res.size(), 2);
        Assertions.assertEquals(res.get(0).getId(), 1);
        Assertions.assertEquals(res.get(1).getId(), 2);
    }
}
