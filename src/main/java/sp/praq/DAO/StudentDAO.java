package sp.praq.DAO;

import sp.praq.HibernateUtil;
import sp.praq.models.*;

import java.time.LocalDateTime;
import java.util.*;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class StudentDAO extends CommonDAO<Student> {
    public StudentDAO() {
        super(Student.class);
    }

    public List<Course> findCourses(Student student) {
        List<StudentGroup> my_list = student.getGroups_of_student();
        List<Course> res = new ArrayList<Course>();
        my_list.forEach(gs -> res.add(gs.getGroup_id().getCourse_id()));
        return res;
    }

    public List<Student> search(String surname, String name, String patronymic) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            StringBuilder queryString = new StringBuilder("FROM Student");
            queryString.append(" WHERE surname = :surname");

            if (name != null) queryString.append(" AND name = :name");
            if (patronymic != null) queryString.append(" AND patronymic = :patronymic");

            TypedQuery<Student> query = session.createQuery(queryString.toString(), Student.class);

            query.setParameter("surname", surname);
            if (name != null) query.setParameter("name", name);
            if (patronymic != null) query.setParameter("patronymic", patronymic);

            List<Student> res = query.getResultList();
            t.commit();

            return res;
        }
    }

    public List<Lesson> getSchedule(Student student, LocalDateTime start, LocalDateTime end) {
        List<StudentGroup> my_list = student.getGroups_of_student();
        List<Group> g = new ArrayList<Group>();
        List<Lesson> res = new ArrayList<Lesson>();
        my_list.forEach(gs -> g.add(gs.getGroup_id()));
        g.forEach(i -> res.addAll(i.getClasses_of_group()));

        for (int i = 0; i < res.size(); i++) {
            LocalDateTime dt = res.get(i).getClass_datetime();
            if (dt.compareTo(start) < 0 || dt.compareTo(end) > 0) {
                res.remove(i);
                i--;
            }
        }

        return res;
    }
}
