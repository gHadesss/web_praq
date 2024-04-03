package sp.praq;

import sp.praq.models.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    private HibernateUtil(){}

    public static SessionFactory getSessionFactory(){
        if (sessionFactory == null) {
            final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().build();
            try {
                sessionFactory = new MetadataSources(registry)
                        .addAnnotatedClasses(Company.class,
                                             Course.class,
                                             Tutor.class,
                                             Student.class,
                                             Group.class,
                                             StudentGroup.class,
                                             Lesson.class)
                        .buildMetadata()
                        .buildSessionFactory();
            }
            catch (Exception e) {
                System.out.println("getSessionFactory error");
                throw e;
            }
        }
        return sessionFactory;
    }
}