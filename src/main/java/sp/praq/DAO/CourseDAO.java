package sp.praq.DAO;

import jakarta.persistence.TypedQuery;
import sp.praq.HibernateUtil;
import sp.praq.models.*;

import java.util.*;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class CourseDAO extends CommonDAO<Course> {
    public CourseDAO() { super(Course.class); }

    public List<Course> listByCompany() {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            StringBuilder queryString = new StringBuilder("SELECT c FROM Course c");
            queryString.append(" LEFT JOIN FETCH c.company_id");
            queryString.append(" ORDER BY c.company_id.title ASC");

            TypedQuery<Course> query = session.createQuery(queryString.toString(), Course.class);
            List<Course> res = query.getResultList();
            t.commit();

            return res;
        }
    }

    public List<Course> listByTitle() {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            StringBuilder queryString = new StringBuilder("SELECT c FROM Course c");
            queryString.append(" ORDER BY c.title ASC");

            TypedQuery<Course> query = session.createQuery(queryString.toString(), Course.class);
            List<Course> res = query.getResultList();
            t.commit();

            return res;
        }
    }
}
