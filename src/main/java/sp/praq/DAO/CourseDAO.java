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
            StringBuilder queryString = new StringBuilder("FROM Course");
            queryString.append(" LEFT JOIN Course.company_id");
            queryString.append(" ORDER BY Company.name ASC");

            TypedQuery<Course> query = session.createQuery(queryString.toString(), Course.class);
            List<Course> res = query.getResultList();
            t.commit();

            return res;
        }
    }

    public List<Course> listByTitle() {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            StringBuilder queryString = new StringBuilder("FROM Course");
//            queryString.append(" LEFT JOIN Course.company_id");
            queryString.append(" ORDER BY Course.title ASC");

            TypedQuery<Course> query = session.createQuery(queryString.toString(), Course.class);
            List<Course> res = query.getResultList();
            t.commit();

            return res;
        }
    }
}
