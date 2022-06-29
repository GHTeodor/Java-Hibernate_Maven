import models.Card;
import models.Passport;
import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Passport.class)
                .addAnnotatedClass(Card.class)
                .getMetadataBuilder()
                .build();

        SessionFactory sessionFactory = metadata
                .getSessionFactoryBuilder()
                .build();

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.save(new User("Olya", new Passport("SD"),
                Arrays.asList(new Card(1234567890123456789L), new Card(987654321098765432L))));
        session.save(new User("Kolya", new Passport("SK"),
                Arrays.asList(new Card(1234567890987654321L), new Card(987654321012345678L))));
        session.save(new User("Petya", new Passport("HS"),
                Arrays.asList(new Card(1234567890012345678L), new Card(987654321001234567L))));
        session.save(new User("Vasya", new Passport("DT"),
                Arrays.asList(new Card(1234567890123456798L), new Card(987654321012345679L))));

        session.getTransaction().commit();

        List<Passport> passports = session.createQuery("select p from Passport p", Passport.class).getResultList();
        passports.forEach(System.out::println);

        List<User> users = session.createQuery("select u from User u", User.class).getResultList();
        users.forEach(user -> System.out.println(user + "" + user.getCards()));

        session.createQuery("select c.user from Card c", User.class).getResultList().forEach(System.out::println);

        session.close();
        sessionFactory.close();
    }
}
