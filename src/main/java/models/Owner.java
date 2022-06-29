package models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "driver")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Owner implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private List<Car> cars;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "licence_id")
    private DriverLicense driverLicense;

    public Owner(String name, List<Car> cars, DriverLicense driverLicense) {
        this.name = name;
        this.cars = cars;
        this.driverLicense = driverLicense;
    }
}
