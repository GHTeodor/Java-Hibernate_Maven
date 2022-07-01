package models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "passport_id")
    @ToString.Exclude
    private Passport passport;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_card",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id")
    )
    private List<Card> cards;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_sg",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "sg_id")
    )
    private List<Sunglass> sunglasses;

    public User(String name) {
        this.name = name;
    }

    public User(String name, Passport passport) {
        this.name = name;
        this.passport = passport;
    }

    public User(String name, List<Sunglass> sunglasses) {
        this.name = name;
        this.sunglasses = sunglasses;
    }
}
