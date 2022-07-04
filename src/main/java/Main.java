import models.*;
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
                .addAnnotatedClass(Colour.class)
                .addAnnotatedClass(DriverLicense.class)
                .getMetadataBuilder()
                .build();

        SessionFactory sessionFactory = metadata
                .getSessionFactoryBuilder()
                .build();

        Session session = sessionFactory.openSession();

        session.beginTransaction();

//        session.save(new Owner("Dominic Toretto",
//                Arrays.asList(new Car("Dodge Charger SRT Hellcat", Type.SEDAN, 717, 15000, 1970), new Car()),
//                new DriverLicense("SS")));
//        session.save(new Owner("Brian O'Conner",
//                Arrays.asList(new Car("Pontiac Fiero", Type.SEDAN, 700, 15000, 2003), new Car()),
//                new DriverLicense("HG")));
//        session.save(new Owner("Letty Ortiz",
//                Arrays.asList(new Car("Lykan HyperSport", Type.ROADSTER, 750, 30000, 2013), new Car()),
//                new DriverLicense("RP")));
//        session.save(new Owner("Mia Toretto",
//                Arrays.asList(new Car("Lamborghini Murcielago", Type.CABRIOLET, 640, 35000, 2001), new Car()),
//                new DriverLicense("SK")));

        session.getTransaction().commit();

//        session.createQuery("select o from Owner o", Owner.class).getResultList().forEach(System.out::println);

        session.beginTransaction(); // @ManyToMany Bidirectional

        Owner owner1 = new Owner("Dominic Toretto",
                Arrays.asList(new Car("Dodge Charger SRT Hellcat", Type.SEDAN, 717, 15000, 1970), new Car()),
                new DriverLicense("SS"));
        Owner owner2 = new Owner("Brian O'Conner",
                Arrays.asList(new Car("Pontiac Fiero", Type.SEDAN, 700, 15000, 2003), new Car()),
                new DriverLicense("HG"));

        Colour colour1 = new Colour(Arrays.asList(owner1, owner2), 900);

        Owner owner3 = new Owner("Letty Ortiz",
                Arrays.asList(new Car("Lykan HyperSport", Type.ROADSTER, 750, 30000, 2013), new Car()),
                new DriverLicense("RP"));

        Owner owner4 = new Owner("Mia Toretto",
                Arrays.asList(new Car("Lamborghini Murcielago", Type.CABRIOLET, 640, 35000, 2001), new Car()),
                new DriverLicense("SK"));

        Colour colour2 = new Colour(Arrays.asList(owner3, owner4), 100);

        session.save(colour1);
        session.save(colour2);
        session.save(new Owner("Hattie Shaw",
                Arrays.asList(new Car("Plymouth", Type.LIMOUSINE, 640, 35000, 2010), new Car()),
                new DriverLicense("СІ"),
                Arrays.asList(colour1, colour2)));

        session.getTransaction().commit();

        session.createQuery("select o from Owner o", Owner.class).getResultList().forEach(System.out::println);
        session.createQuery("select c from Colour c", Colour.class).getResultList().forEach(colour -> System.out.println(colour.getDrivers()));

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
//      name,
//      List<Car>,
//      List<Colour>,
//      DriveLicense

// Colour
//      id,
//      List<Owner>,
//      price

// DriveLicense
//      id,
//      series
//
// За допомогою хібернейту реалізувати наступну структуру
// зв'язок Bidirectional!!!