package sp.praq.DAO;

import sp.praq.models.*;
import sp.praq.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class StudentGroupDAO extends M2MCommonDAO<StudentGroup> {
    public StudentGroupDAO() { super(StudentGroup.class); }

    public StudentGroup findByObj(Student student, Group group) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        try {
            StringBuilder queryString = new StringBuilder(
                    "SELECT sg FROM StudentGroup sg " +
                            "WHERE sg.student_id = :student " +
                            "AND sg.group_id = :group");

            TypedQuery<StudentGroup> query = session.createQuery(queryString.toString(), StudentGroup.class);

            query.setParameter("student", student);
            query.setParameter("group", group);

            StudentGroup res = query.getSingleResult();
            t.commit();
            return res;
        } catch (Exception e) {
//            System.out.println("findByObj error: " + e);
            t.rollback();
            throw new Exception("Запрошенного объекта StudentGroup не существует.");
//            return null;
        }

//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        Transaction t = session.beginTransaction();
//        StringBuilder queryString = new StringBuilder(
//                "SELECT sg FROM StudentGroup sg " +
//                        "WHERE sg.student_id = :student " +
//                        "AND sg.group_id = :group");
//
//        TypedQuery<StudentGroup> query = session.createQuery(queryString.toString(), StudentGroup.class);
//
//        query.setParameter("student", student);
//        query.setParameter("group", group);
//
//        StudentGroup res = query.getSingleResult();
//        t.commit();
//
//        if (res == null) throw new Exception("Запрошенного объекта StudentGroup не существует.");
//        return res;
    }

    public void deleteByObj(Student student, Group group) throws Exception {
//        StudentGroup obj = findByObj(student, group);
//        if (obj != null) {
//            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//                Transaction t = session.beginTransaction();
//                session.remove(obj);
//                t.commit();
//            }
//        } else {
////            System.out.println("deleteByObj error");
//        }

        StudentGroup obj = findByObj(student, group);
        if (obj != null) {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction t = session.beginTransaction();
            session.remove(obj);
            t.commit();
        }
    }
}
