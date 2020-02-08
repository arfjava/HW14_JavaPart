package ir.maktab.homeworks.hw14.entities;

import ir.maktab.homeworks.hw14.utilities.TransactionType;
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
    private Long id;

    private TransactionType transactionType;

    Date date;

    Long amount;

    Boolean isSuccessful;

    private String description;

    @ManyToOne
    private Account account;
}
