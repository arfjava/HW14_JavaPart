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
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long srcAccountId;
    Long dstAccountId;

    Date date;

    Long amount;

    Boolean isSuccessful;

    @ManyToOne
    Account account;

}
