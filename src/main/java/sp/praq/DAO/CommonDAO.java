package sp.praq.DAO;

import sp.praq.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

// company
// course
// tutor
// student
// group

public abstract class CommonDAO<T> {
    private final Class<T> entityClass;

    public CommonDAO(Class<T> entityClass){
        this.entityClass = entityClass;
    }

    public T findById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            T t = session.get(entityClass, id);
            transaction.commit();
            return t;
        }
    }

    public List<T> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            List<T> resultList = session.createQuery("from " + entityClass.getSimpleName(), entityClass).getResultList();
            transaction.commit();
            return resultList;
        }
    }

    public void save(T obj) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.persist(obj);
                transaction.commit();
            } catch (Exception e) {
                System.out.println("save error: " + e);
                transaction.rollback();
            }
        }
    }

    public void update(T obj) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.merge(obj);
                transaction.commit();
            } catch (Exception e) {
                System.out.println("update error " + e);
                transaction.rollback();
            }
        }
    }

    public void delete(T obj) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
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

    public void deleteById(Integer id) {
        T obj = findById(id);
        if (obj != null) {
            try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
                Transaction transaction = session.beginTransaction();
                session.remove(obj);
                transaction.commit();
            }
        } else {
            System.out.println("deleteById error");
        }
    }
}
