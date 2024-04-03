package sp.praq.services;

import sp.praq.models.*;
import sp.praq.DAO.StudentDAO;

import java.time.LocalDateTime;
import java.util.*;

public class StudentService extends CommonService<Student, StudentDAO> {
    public StudentService() { super(new StudentDAO()); }

    public List<Course> findCourses(Student student) { return dao.findCourses(student); }

    public List<Student> search(String surname, String name, String patronymic) {
        return dao.search(surname, name, patronymic);
    }

    public List<Lesson> getSchedule(Student student, LocalDateTime start, LocalDateTime end) {
        return dao.getSchedule(student, start, end);
    }
}
