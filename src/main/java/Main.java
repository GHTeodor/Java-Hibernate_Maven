import models.Card;
import models.Passport;
import models.Sunglass;
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
                .addAnnotatedClass(Sunglass.class)
                .getMetadataBuilder()
                .build();

        SessionFactory sessionFactory = metadata
                .getSessionFactoryBuilder()
                .build();

        Session session = sessionFactory.openSession();

        session.beginTransaction();
//        User vivaldi = new User("Vivaldi");
//        System.out.println(vivaldi);
//        Card card1 = new Card(1234567890123456789L, vivaldi);
//        System.out.println(vivaldi);
//        Card card2 = new Card(987654321098765432L, vivaldi);
//        System.out.println(vivaldi);
//        session.save(card1);
//        session.save(card2);
//        System.out.println(vivaldi);

//        User user = new User("Riko", Arrays.asList(new Sunglass("Sokoban"), new Sunglass("RayBan")));
//        session.save(user);
//        System.out.println(user);

        List<Sunglass> sunglasses = Arrays.asList(new Sunglass("Sokoban"), new Sunglass("RayBan"));
        User user1 = new User("Rick", sunglasses);
        User user2 = new User("Morty", sunglasses);
        session.save(user1);
        session.save(user2);
        System.out.println(user1 + "\n" + user2);

        session.getTransaction().commit();

        session.close();
        sessionFactory.close();
    }
}
