import models.Car;
import models.Owner;
import models.Type;
import models.Word;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(Word.class)
                .addAnnotatedClass(Car.class)
                .addAnnotatedClass(Owner.class)
                .getMetadataBuilder()
                .build();

        SessionFactory sessionFactory = metadata
                .getSessionFactoryBuilder()
                .build();

        Session session = sessionFactory.openSession();
        // 1
        session.beginTransaction();

        session.save(new Word("Citrus"));
        session.save(new Word("Mango"));
        session.save(new Word("Orange"));
        session.save(new Word("Grapes"));

        List<Word> words = session.createQuery("select w from Word w", Word.class).getResultList();
        System.out.println(words);

        session.getTransaction().commit();
        // 2
        session.beginTransaction();

        session.save(new Owner("Dominic Toretto", new Car("Dodge Charger SRT Hellcat", Type.SEDAN, 717, 15000, 1970)));
        session.save(new Owner("Brian O'Conner", new Car("Pontiac Fiero", Type.SEDAN, 700, 15000, 2003)));
        session.save(new Owner("Letty Ortiz", new Car("Lykan HyperSport", Type.ROADSTER, 750, 30000, 2013)));
        session.save(new Owner("Mia Toretto", new Car("Lamborghini Murcielago", Type.CABRIOLET, 640, 35000, 2001)));

        session.createNativeQuery("select * from driver", Owner.class).getResultList().forEach(System.out::println);

        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }
}
// використовуючи hibernate:
//     - створити табличку Word (id, value)
//     - наповнити її
//     - дістати всі value слів та запакувати в List .
//
//
//     Створити клас Car з полями:
//     id
//     model,
//     Type (ENUM)
//     power,
//     price,
//     year.
//
//     Створити клас Owner
//     id,
//     name
//     Car
//
//     Зв'язок між авто та водієм 1:1 Unidirectional (Власник знає про авто, авто про власника не знає нічого)