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

    @Column(nullable = false)
    String expirationDate;

    @Column(nullable = false)
    Integer cvv2;

    @Column(nullable = false)
    String password;

    @Column(nullable = false)
    String secondPassword;

    public CardPasswordInfo(String expirationDate, Integer cvv2, String password, String secondPassword) {
        this.expirationDate = expirationDate;
        this.cvv2 = cvv2;
        this.password = password;
        this.secondPassword = secondPassword;
    }

}
