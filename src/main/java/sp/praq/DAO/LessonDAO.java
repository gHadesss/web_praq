package sp.praq.DAO;

import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sp.praq.HibernateUtil;
import sp.praq.models.*;
import java.time.LocalDateTime;

public class LessonDAO extends M2MCommonDAO<Lesson> {
    public LessonDAO() { super(Lesson.class); }

    public Lesson findByObj(Group group, LocalDateTime class_dt, Integer room, Double duration) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        try {
            StringBuilder queryString = new StringBuilder(
                    "SELECT l FROM Lesson l LEFT JOIN FETCH l.group_id g " +
                            "WHERE g.id = :group " +
                            "AND l.class_datetime = :class_dt " +
                            "AND l.room_number = :room " +
                            "AND l.class_duration = :duration");

            TypedQuery<Lesson> query = session.createQuery(queryString.toString(), Lesson.class);

            query.setParameter("group", group.getId());
            query.setParameter("class_dt", class_dt);
            query.setParameter("room", room);
            query.setParameter("duration", duration);

            Lesson res = query.getSingleResult();
            t.commit();
            return res;
        } catch (Exception e) {
//            System.out.println("findByObj error: " + e);
            t.rollback();
            throw new Exception("Нет такого занятия.");
//            return null;

        }

//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        Transaction t = session.beginTransaction();
//        StringBuilder queryString = new StringBuilder(
//                "SELECT l FROM Lesson l LEFT JOIN FETCH l.group_id g " +
//                        "WHERE g.id = :group " +
//                        "AND l.class_datetime = :class_dt " +
//                        "AND l.room_number = :room " +
//                        "AND l.class_duration = :duration");
//
//        TypedQuery<Lesson> query = session.createQuery(queryString.toString(), Lesson.class);
//
//        query.setParameter("group", group.getId());
//        query.setParameter("class_dt", class_dt);
//        query.setParameter("room", room);
//        query.setParameter("duration", duration);
//
//        Lesson res = query.getSingleResult();
//        t.commit();
//
//        if (res == null) throw new Exception("Нет такого занятия.");
//        return res;
    }

    public void deleteByObj(Group group, LocalDateTime class_dt, Integer room, Double duration) throws Exception {
//        Lesson obj = findByObj(group, class_dt, room, duration);
//        if (obj != null) {
//            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//                Transaction t = session.beginTransaction();
//                try {
//                    session.remove(obj);
//                    t.commit();
//                } catch (Exception e) {
//                    System.out.println("deleteByObj error: " + e);
//                    t.rollback();
//                }
//            }
//        }

        Lesson obj = findByObj(group, class_dt, room, duration);
        if (obj != null) {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction t = session.beginTransaction();
            session.remove(obj);
            t.commit();
        }
//        else {
//            throw new Exception("Нет такого занятия.");
//        }
    }
}
