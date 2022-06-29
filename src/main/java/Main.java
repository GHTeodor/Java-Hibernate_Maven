import models.Car;
import models.DriverLicense;
import models.Owner;
import models.Type;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(Car.class)
                .addAnnotatedClass(Owner.class)
                .addAnnotatedClass(DriverLicense.class)
                .getMetadataBuilder()
                .build();

        SessionFactory sessionFactory = metadata
                .getSessionFactoryBuilder()
                .build();

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.save(new Owner("Dominic Toretto",
                Arrays.asList(new Car("Dodge Charger SRT Hellcat", Type.SEDAN, 717, 15000, 1970),new Car()),
                new DriverLicense("SD")));
        session.save(new Owner("Brian O'Conner",
                Arrays.asList(new Car("Pontiac Fiero", Type.SEDAN, 700, 15000, 2003),new Car()),
                new DriverLicense("SD")));
        session.save(new Owner("Letty Ortiz",
                Arrays.asList(new Car("Lykan HyperSport", Type.ROADSTER, 750, 30000, 2013),new Car()),
                new DriverLicense("SD")));
        session.save(new Owner("Mia Toretto",
                Arrays.asList(new Car("Lamborghini Murcielago", Type.CABRIOLET, 640, 35000, 2001),new Car()),
                new DriverLicense("SD")));

        session.getTransaction().commit();

        session.createQuery("select o from Owner o",Owner.class).getResultList().forEach(System.out::println);

        session.close();
        sessionFactory.close();
    }
}
// Car
//      id
//      model,
//      Type (ENUM)
//      power,
//      price,
//      year

// Owner
//      id,
//      name
//      List<Car>
//      DriveLicense

// DriveLicense
//      id
//      series
//
// За допомогою хібернейту реалізувати наступну структуру
// зв'язок unidirectional!!!