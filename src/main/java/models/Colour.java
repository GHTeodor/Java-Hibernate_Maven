package models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Colour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "drivers_colours",
            joinColumns = @JoinColumn(name = "colour_id"),
            inverseJoinColumns = @JoinColumn(name = "driver_id")
    )
    private List<Owner> drivers;
    private int price;

    public Colour(List<Owner> drivers, int price) {
        this.drivers = drivers;
        this.price = price;
    }
}
