package models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_table")
@Getter
@Setter
@ToString(exclude = {"passport"})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username")
    private String name;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @PrimaryKeyJoinColumn
    @JoinColumn(name = "passport_id")
    private Passport passport;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ElementCollection
    private List<String> skills = new ArrayList<>();

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public User(String name, Passport passport) {
        this.name = name;
        this.passport = passport;
    }

    public User(String name, Passport passport, Gender gender) {
        this.name = name;
        this.passport = passport;
        this.gender = gender;
    }

    public User(String name, Passport passport, Gender gender, List<String> skills) {
        this.name = name;
        this.passport = passport;
        this.gender = gender;
        this.skills = skills;
    }
}
