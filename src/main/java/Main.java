import models.Gender;
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
                .getMetadataBuilder()
                .build();

        SessionFactory sessionFactory = metadata
                .getSessionFactoryBuilder()
                .build();

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.save(new User("ananas", new Passport("SI-76543"), Gender.MALE, Arrays.asList("Java", "JS")));
        session.save(new User("banana", new Passport("SI-00001"), Gender.FEMALE, Arrays.asList("Java", "HTML")));
        session.save(new User("cherry", new Passport("SI-67894"), Gender.MALE, Arrays.asList("Java", "C#")));
        session.save(new User("tomato", new Passport("SI-12355"), Gender.FEMALE, Arrays.asList("Java", "Mongo")));
        session.save(new User("papaya", new Passport("SI-55512"), Gender.MALE, Arrays.asList("Java", "MySQL")));

        session.getTransaction().commit();

//        List<User> users = session.createNativeQuery("select * from  user_table", User.class).getResultList();
//        List<User> users = session.createQuery("select u from  User u", User.class).getResultList();
//        List<User> users = session
//                .createQuery("select u from  User u where  u.id>:id and u.name!=:name", User.class)
//                .setParameter("id", 2)
//                .setParameter("name", "banana")
//                .getResultList();
//        System.out.println(users);

//        User user = session.find(User.class, 1);
//        System.out.println(user);
//        user.setName("new fruit1");
//
        session.beginTransaction();
////        session.update(user);
//        session.delete("user_table", user);

        session.getTransaction().commit();

        session.createQuery("select u from User u", User.class).getResultList().forEach(System.out::println);

        session.close();
        sessionFactory.close();
    }
}