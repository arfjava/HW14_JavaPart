package ir.maktab.homeworks.hw14.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CardPasswordInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Date expirationDate;
    Integer cvv2;
    String secondPassword;

    @OneToOne
    Card card;
}
