package ir.maktab.homeworks.hw14.entities;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    Boolean isActive;

    @Column(nullable = false, unique = true)
    String cardNumber;


    @OneToOne( cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    CardPasswordInfo cardPasswordInfo;

}
