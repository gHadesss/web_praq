package sp.praq;

import org.junit.Assert;
import sp.praq.models.*;
import sp.praq.services.*;
import org.junit.jupiter.api.*;
import java.util.*;
import java.time.*;

public class StudentGroupTest {
    @Test
    public void testStudentGroup() {
        try {
            GroupService g_s = new GroupService();
            StudentService s_s = new StudentService();
            StudentGroup sg = new StudentGroup(s_s.findById(1), g_s.findById(2));

            Assertions.assertEquals(g_s.findById(2), sg.getGroup_id());
            Assertions.assertEquals(s_s.findById(1), sg.getStudent_id());
        } catch (Exception e) {
            Assertions.assertEquals(0, 1);
        }
    }

    @Test
    public void testFindAll() {
        StudentGroupService sg_s = new StudentGroupService();
        List<StudentGroup> lsg = sg_s.findAll();

        Assertions.assertEquals(3, lsg.size());
    }

    @Test
    public void testFindByObj() {
        try {
            GroupService g_s = new GroupService();
            StudentService s_s = new StudentService();
            StudentGroupService sg_s = new StudentGroupService();

            List<StudentGroup> lsg = sg_s.findAll();
            StudentGroup sg = lsg.get(0);
            StudentGroup found = sg_s.findByObj(sg.getStudent_id(), sg.getGroup_id());
            Assertions.assertEquals(sg, found);
        } catch (Exception e) {
            Assertions.assertEquals(0, 1);
        }
    }

    @Test
    public void testUpdateDelete() {
        try {
            GroupService g_s = new GroupService();
            Group g = g_s.findById(2);

            StudentService s_s = new StudentService();
            Student s = s_s.findById(1);

            StudentGroupService sg_s = new StudentGroupService();
            StudentGroup sg = new StudentGroup(s, g);

            sg_s.update(sg);
            StudentGroup found = sg_s.findByObj(sg.getStudent_id(), sg.getGroup_id());
            Assertions.assertEquals(sg, found);

            sg_s.delete(sg);
            Student newStudent = new Student("Студент", "123", "123", "123", "123");
            s_s.save(newStudent);
            sg.setStudent_id(newStudent);
            sg_s.update(sg);
            found = sg_s.findByObj(newStudent, sg.getGroup_id());
            Assertions.assertEquals(sg, found);

            sg_s.delete(sg);
            s_s.delete(newStudent);
            found = sg_s.findByObj(sg.getStudent_id(), sg.getGroup_id());
//            Assertions.assertEquals(null, found);
        } catch (Exception e) {
            Assertions.assertEquals("java.lang.Exception: Запрошенного объекта StudentGroup не существует.", e.toString());
        }
    }

    @Test
    public void testDeleteByObj() {
        try {
            GroupService g_s = new GroupService();
            Group g = g_s.findById(2);

            StudentService s_s = new StudentService();
            Student s = s_s.findById(1);

            StudentGroupService sg_s = new StudentGroupService();
            StudentGroup sg = new StudentGroup(s, g);
            sg_s.update(sg);

            sg_s.deleteByObj(s, g);
            StudentGroup found = sg_s.findByObj(sg.getStudent_id(), sg.getGroup_id());
//            Assertions.assertEquals(null, found);
        } catch (Exception e) {
            Assertions.assertEquals("java.lang.Exception: Запрошенного объекта StudentGroup не существует.", e.toString());
        }
    }
}
