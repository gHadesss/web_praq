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

    public List<Student> search(String surname, String name, String patronymic) {
        if (surname == null && name == null && patronymic == null) return this.findAll();

//        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
//            Transaction t = session.beginTransaction();
//            StringBuilder queryString = new StringBuilder("SELECT s FROM Student s");
//            if (surname != null) queryString.append(" WHERE s.surname LIKE :surname");
//
//            if (name != null) {
//                if (surname != null) queryString.append(" AND name LIKE :name");
//                else queryString.append(" name LIKE :name");
//            }
//
//            if (patronymic != null) {
//                if (surname != null || name != null) queryString.append(" AND patronymic LIKE :patronymic");
//                else queryString.append(" patronymic LIKE :patronymic");
//            }
//
//            TypedQuery<Student> query = session.createQuery(queryString.toString(), Student.class);
//
//            if (surname != null) query.setParameter("surname", "%" + surname + "%");
//            if (name != null) query.setParameter("name", "%" + name + "%");
//            if (patronymic != null) query.setParameter("patronymic", "%" + patronymic + "%");
//
//            List<Student> res = query.getResultList();
//            t.commit();
//
//            return res;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        StringBuilder queryString = new StringBuilder("SELECT s FROM Student s");
        if (surname != null) queryString.append(" WHERE s.surname LIKE :surname");

        if (name != null) {
            if (surname != null) queryString.append(" AND name LIKE :name");
            else queryString.append(" name LIKE :name");
        }

        if (patronymic != null) {
            if (surname != null || name != null) queryString.append(" AND patronymic LIKE :patronymic");
            else queryString.append(" patronymic LIKE :patronymic");
        }

        TypedQuery<Student> query = session.createQuery(queryString.toString(), Student.class);

        if (surname != null) query.setParameter("surname", "%" + surname + "%");
        if (name != null) query.setParameter("name", "%" + name + "%");
        if (patronymic != null) query.setParameter("patronymic", "%" + patronymic + "%");

        List<Student> res = query.getResultList();
        t.commit();

        return res;
    }

    public List<Lesson> getSchedule(Student student, LocalDateTime start, LocalDateTime end) {
//        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
//            Transaction t = session.beginTransaction();
//
//            StringBuilder queryString = new StringBuilder("SELECT l FROM Lesson l");
//            queryString.append(" LEFT JOIN FETCH l.group_id g");
//            queryString.append(" LEFT JOIN FETCH g.students_of_group sg");
//            queryString.append(" LEFT JOIN FETCH sg.student_id s");
//            queryString.append(" WHERE s.id = :student");
//            queryString.append(" AND l.class_datetime >= :start");
//            queryString.append(" AND l.class_datetime <= :end");
//            queryString.append(" ORDER BY l.class_datetime ASC");
//
//            TypedQuery<Lesson> query = session.createQuery(queryString.toString(), Lesson.class);
//            query.setParameter("student", student.getId());
//            query.setParameter("start", start);
//            query.setParameter("end", end);
//
//            List<Lesson> res = query.getResultList();
//            t.commit();
//
//            return res;
//        }

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();

        StringBuilder queryString = new StringBuilder("SELECT l FROM Lesson l");
        queryString.append(" LEFT JOIN FETCH l.group_id g");
        queryString.append(" LEFT JOIN FETCH g.students_of_group sg");
        queryString.append(" LEFT JOIN FETCH sg.student_id s");
        queryString.append(" WHERE s.id = :student");
        queryString.append(" AND l.class_datetime >= :start");
        queryString.append(" AND l.class_datetime <= :end");
        queryString.append(" ORDER BY l.class_datetime ASC");

        TypedQuery<Lesson> query = session.createQuery(queryString.toString(), Lesson.class);
        query.setParameter("student", student.getId());
        query.setParameter("start", start);
        query.setParameter("end", end);

        List<Lesson> res = query.getResultList();
        t.commit();

        return res;
    }
}
