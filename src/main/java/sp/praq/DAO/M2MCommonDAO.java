package sp.praq.DAO;

import sp.praq.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

// classes
// groups_of_students

public abstract class M2MCommonDAO<T> {
    private Class<T> entityClass;

    public M2MCommonDAO(Class<T> entityClass){
        this.entityClass = entityClass;
    }

    public List<T> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            List<T> resultList = session.createQuery("from " + entityClass.getSimpleName(), entityClass).getResultList();
            transaction.commit();
            return resultList;
        }
    }

    public void update(T obj) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.merge(obj);
                transaction.commit();
            } catch (Exception e) {
                System.out.println("update error: " + e);
                transaction.rollback();
            }
        }
    }

    public void delete(T obj) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.remove(obj);
                transaction.commit();
            } catch (Exception e) {
                System.out.println("delete error: " + e);
                transaction.rollback();
            }
        }
    }
}
