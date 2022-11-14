package net.alkitmessenger.util;

import lombok.experimental.UtilityClass;
import net.alkitmessenger.user.User;
import net.alkitmessenger.user.message.Message;
import net.alkitmessenger.user.message.PrivateMessages;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Optional;
import java.util.function.Consumer;

@UtilityClass
public class HibernateUtil {

    private static Configuration configuration;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null)
            try {

                configuration = new Configuration().configure()
                        .addAnnotatedClass(User.class)
                        .addAnnotatedClass(Message.class)
                        .addAnnotatedClass(PrivateMessages.class);

                sessionFactory = configuration.buildSessionFactory(new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build());

            } catch (Exception exception) {

                exception.printStackTrace();

            }

        return sessionFactory;

    }

    public static void registerAnnotatedClass(Class<?> clazz) {

        if (sessionFactory == null)
            getSessionFactory();

        configuration.addAnnotatedClass(clazz);
        sessionFactory = configuration.buildSessionFactory(new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build());

    }

    public static <T> Optional<T> getAndCallAction(Class<T> entityType, Object object, Consumer<T> consumer) {

        Optional<T> receivedOptional = get(entityType, object);
        receivedOptional.ifPresent(consumer);

        return receivedOptional;

    }

    public static <T> Optional<T> get(Class<T> entityType, Object object) {

        return Optional.ofNullable(getSessionFactory().openSession().get(entityType, object));

    }

    public static <T> Optional<Query<T>> createQuery(String queryString, Class<T> resultClass) {

        return Optional.ofNullable(getSessionFactory().openSession().createQuery(queryString, resultClass).setCacheable(false));

    }

    public static <T> void createQueryAndCallActionForEach(String queryString, Class<T> resultClass, Consumer<T> consumer) {

        Optional.ofNullable(getSessionFactory()
                        .openSession()
                        .createQuery(queryString, resultClass)
                        .setCacheable(false))
                .ifPresent(query -> query.list().forEach(consumer));

    }

    public static void saveOrUpdate(Object object) {

        if (object != null) {

            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.saveOrUpdate(object);

            transaction.commit();

        }
    }
}