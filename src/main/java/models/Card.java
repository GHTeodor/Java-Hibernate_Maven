package models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Card implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private long number;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_card",
            joinColumns = @JoinColumn(name = "card_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private User user;

    public Card(long number) {
        this.number = number;
    }

    public Card(long number, User user) {
        this.number = number;
        this.user = user;
    }
}