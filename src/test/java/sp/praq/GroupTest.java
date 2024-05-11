package sp.praq;

import org.junit.Assert;
import sp.praq.models.*;
import sp.praq.services.*;
import org.junit.jupiter.api.*;
import java.util.*;
import java.time.*;

public class GroupTest {
    @Test
    public void testGroup() {
        CourseService cs = new CourseService();
        TutorService ts = new TutorService();
        GroupService gs = new GroupService();
        Group g = new Group(cs.findById(1), ts.findById(1));

        Assertions.assertEquals(cs.findById(1), g.getCourse_id());
        Assertions.assertEquals(ts.findById(1), g.getTutor_id());
    }

    @Test
    public void testFindById() {
        GroupService gs = new GroupService();
        CourseService cs = new CourseService();
        TutorService ts = new TutorService();

        Group g = gs.findById(1);
        Assertions.assertEquals(cs.findById(1), g.getCourse_id());
        Assertions.assertEquals(ts.findById(2), g.getTutor_id());

        g = gs.findById(2);
        Assertions.assertEquals(cs.findById(2), g.getCourse_id());
        Assertions.assertEquals(ts.findById(1), g.getTutor_id());
    }

    @Test
    public void testFindAll() {
        GroupService gs = new GroupService();
        List<Group> lg = gs.findAll();

        Assertions.assertEquals(lg.size(), 2);
    }

    @Test
    public void testSaveUpdateDelete() {
        CourseService cs = new CourseService();
        TutorService ts = new TutorService();
        GroupService gs = new GroupService();
        Group g = new Group(cs.findById(1), ts.findById(1));

        gs.save(g);
        Group found = gs.findById(g.getId());
        Assertions.assertEquals(g, found);

        g.setCourse_id(cs.findById(2));
        g.setTutor_id(ts.findById(2));
        gs.update(g);
        found = gs.findById(g.getId());
        Assertions.assertEquals(g, found);

        gs.delete(g);
        found = gs.findById(g.getId());
        Assertions.assertEquals(null, found);
    }

    @Test
    public void testDeleteById() {
        CourseService cs = new CourseService();
        TutorService ts = new TutorService();
        GroupService gs = new GroupService();
        Group g = new Group(cs.findById(1), ts.findById(1));
        gs.save(g);
        Group found = gs.findById(g.getId());
        Assertions.assertEquals(g, found);
        gs.deleteById(g.getId());
        found = gs.findById(g.getId());
        Assertions.assertEquals(null, found);
    }
}
