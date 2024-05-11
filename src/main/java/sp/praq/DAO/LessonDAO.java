package sp.praq.DAO;

import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sp.praq.HibernateUtil;
import sp.praq.models.*;
import java.time.LocalDateTime;

public class LessonDAO extends M2MCommonDAO<Lesson> {
    public LessonDAO() { super(Lesson.class); }

    public Lesson findByObj(Group group, LocalDateTime class_dt, Integer room, Double duration) {
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
            System.out.println("findByObj error: " + e);
            t.rollback();
            return null;
        }
    }

    public void deleteByObj(Group group, LocalDateTime class_dt, Integer room, Double duration) {
        Lesson obj = findByObj(group, class_dt, room, duration);
        if (obj != null) {
            try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
                Transaction t = session.beginTransaction();
                session.remove(obj);
                t.commit();
            }
        } else {
            System.out.println("deleteByObj error");
        }
    }
}
