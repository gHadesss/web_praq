package sp.praq.DAO;

import sp.praq.HibernateUtil;
import sp.praq.models.*;

import java.time.LocalDateTime;
import java.util.*;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TutorDAO extends CommonDAO<Tutor> {
    public TutorDAO() {
        super(Tutor.class);
    }

    public List<Tutor> listByCompany() {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            StringBuilder queryString = new StringBuilder("FROM Tutor");
            queryString.append(" LEFT JOIN Tutor.company_id");
            queryString.append(" ORDER BY Company.name ASC");

            TypedQuery<Tutor> query = session.createQuery(queryString.toString(), Tutor.class);
            List<Tutor> res = query.getResultList();
            t.commit();

            return res;
        }
    }

    public List<Tutor> search(String surname, String name, String patronymic) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            StringBuilder queryString = new StringBuilder("FROM Tutor");
            queryString.append(" WHERE surname = :surname");

            if (name != null) queryString.append(" AND name = :name");
            if (patronymic != null) queryString.append(" AND patronymic = :patronymic");

            TypedQuery<Tutor> query = session.createQuery(queryString.toString(), Tutor.class);

            query.setParameter("surname", surname);
            if (name != null) query.setParameter("name", name);
            if (patronymic != null) query.setParameter("patronymic", patronymic);

            List<Tutor> res = query.getResultList();
            t.commit();
            return res;
        }
    }

    public List<Lesson> getSchedule(Tutor tutor, LocalDateTime start, LocalDateTime end) {
        List<Group> g = tutor.getTutors_group();
        List<Lesson> res = new ArrayList<Lesson>();
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
