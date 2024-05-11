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

    public List<Tutor> listByCompany(String surname, String name, String patronymic) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            StringBuilder queryString = new StringBuilder("SELECT t FROM Tutor t");

            if (!(surname == null && name == null && patronymic == null)) {
                if (surname != null) queryString.append(" WHERE surname LIKE :surname");

                if (name != null) {
                    if (surname != null) queryString.append(" AND name LIKE :name");
                    else queryString.append(" name LIKE :name");
                }

                if (patronymic != null) {
                    if (surname != null || name != null) queryString.append(" AND patronymic LIKE :patronymic");
                    else queryString.append(" patronymic LIKE :patronymic");
                }
            }

            queryString.append(" ORDER BY t.company_id.title, surname, name, patronymic ASC");
            TypedQuery<Tutor> query = session.createQuery(queryString.toString(), Tutor.class);

            if (surname != null) query.setParameter("surname", "%" + surname + "%");
            if (name != null) query.setParameter("name", "%" + name + "%");
            if (patronymic != null) query.setParameter("patronymic", "%" + patronymic + "%");

            List<Tutor> res = query.getResultList();
            t.commit();

            return res;
        }
    }

    public List<Tutor> listByName(String surname, String name, String patronymic) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            StringBuilder queryString = new StringBuilder("SELECT t FROM Tutor t");

            if (!(surname == null && name == null && patronymic == null)) {
                if (surname != null) queryString.append(" WHERE surname LIKE :surname");

                if (name != null) {
                    if (surname != null) queryString.append(" AND name LIKE :name");
                    else queryString.append(" name LIKE :name");
                }

                if (patronymic != null) {
                    if (surname != null || name != null) queryString.append(" AND patronymic LIKE :patronymic");
                    else queryString.append(" patronymic LIKE :patronymic");
                }
            }

            queryString.append(" ORDER BY t.surname, t.name, t.patronymic ASC");
            TypedQuery<Tutor> query = session.createQuery(queryString.toString(), Tutor.class);

            if (surname != null) query.setParameter("surname", "%" + surname + "%");
            if (name != null) query.setParameter("name", "%" + name + "%");
            if (patronymic != null) query.setParameter("patronymic", "%" + patronymic + "%");

            List<Tutor> res = query.getResultList();
            t.commit();

            return res;
        }
    }

    public List<Lesson> getSchedule(Tutor tutor, LocalDateTime start, LocalDateTime end) {
//        List<Group> g = tutor.getTutors_group();
//        List<Lesson> res = new ArrayList<Lesson>();
//        g.forEach(i -> res.addAll(i.getClasses_of_group()));
//
//        for (int i = 0; i < res.size(); i++) {
//            LocalDateTime dt = res.get(i).getClass_datetime();
//            if (dt.compareTo(start) < 0 || dt.compareTo(end) > 0) {
//                res.remove(i);
//                i--;
//            }
//        }

        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();

            StringBuilder queryString = new StringBuilder("SELECT l FROM Lesson l");
            queryString.append(" LEFT JOIN FETCH l.group_id g");
            queryString.append(" LEFT JOIN FETCH g.tutor_id t");
            queryString.append(" WHERE t.id = :tutor");
            queryString.append(" AND l.class_datetime >= :start");
            queryString.append(" AND l.class_datetime <= :end");
            queryString.append(" ORDER BY l.class_datetime ASC");

            TypedQuery<Lesson> query = session.createQuery(queryString.toString(), Lesson.class);
            query.setParameter("tutor", tutor.getId());
            query.setParameter("start", start);
            query.setParameter("end", end);

            List<Lesson> res = query.getResultList();
            t.commit();

            return res;
        }
    }
}
