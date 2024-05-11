package sp.praq;

import org.junit.Assert;
import sp.praq.models.*;
import sp.praq.services.*;
import org.junit.jupiter.api.*;
import java.util.*;
import java.time.*;

public class LessonTest {
    @Test
    public void testLesson() {
        LessonService l_s = new LessonService();
        GroupService g_s = new GroupService();

        LocalDateTime dt = LocalDateTime.of(2024, 1, 1, 12, 0, 0);
        Lesson l = new Lesson(g_s.findById(2), dt, 1, 0.5);

        Assertions.assertEquals(g_s.findById(2), l.getGroup_id());
        Assertions.assertEquals(1, l.getRoom_number());
        Assertions.assertEquals(dt,l.getClass_datetime());
        Assertions.assertEquals(0.5, l.getClass_duration());
    }

    @Test
    public void testFindAll() {
        LessonService l_s = new LessonService();
        List<Lesson> ll = l_s.findAll();
        Assertions.assertEquals(3, ll.size());
    }

    @Test
    public void testFindByObj() {
        LessonService l_s = new LessonService();
        GroupService g_s = new GroupService();

        List<Lesson> ll = l_s.findAll();
        Lesson l = ll.get(0);
        Lesson found = l_s.findByObj(l.getGroup_id(), l.getClass_datetime(), l.getRoom_number(), l.getClass_duration());
        Assertions.assertEquals(l, found);
    }

    @Test
    public void testUpdateDelete() {
        LessonService l_s = new LessonService();
        GroupService g_s = new GroupService();

        LocalDateTime dt = LocalDateTime.of(2024, 1, 1, 12, 0, 0);
        Lesson l = new Lesson(g_s.findById(2), dt, 1, 0.5);

        l_s.update(l);
        Lesson found = l_s.findByObj(l.getGroup_id(), l.getClass_datetime(), l.getRoom_number(), l.getClass_duration());
        Assertions.assertEquals(l, found);

        l.setRoom_number(2);
        l_s.update(l);
        found = l_s.findByObj(l.getGroup_id(), l.getClass_datetime(), l.getRoom_number(), l.getClass_duration());
        Assertions.assertEquals(l, found);
//        System.out.println(found.toString());
//
//        System.out.println("findbyobj is stoopid");

        l_s.delete(l);
        found = l_s.findByObj(l.getGroup_id(), l.getClass_datetime(), l.getRoom_number(), l.getClass_duration());
        Assertions.assertEquals(null, found);
    }
}
