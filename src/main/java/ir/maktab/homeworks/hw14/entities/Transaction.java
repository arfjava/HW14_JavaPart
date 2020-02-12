package ir.maktab.homeworks.hw14.entities;

import ir.maktab.homeworks.hw14.utilities.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private TransactionType transactionType;

    @Column(nullable = false)
    String date;

    @Column(nullable = false)
    Long amount;

    @Column(nullable = false)
    Boolean isSuccessful;

    private String description;

    @ManyToOne
    private Account account;
}
