package ir.maktab.homeworks.hw14.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Boolean isActive;

    @Column(nullable = false, unique = true)
    String cardNumber;

    @OneToOne
    Account account;

    @OneToOne(mappedBy = "card", cascade = CascadeType.ALL)
    CardPasswordInfo cardPasswordInfo;

}
