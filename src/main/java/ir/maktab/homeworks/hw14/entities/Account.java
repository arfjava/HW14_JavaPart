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
public class Account {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Boolean isActive;

    @ManyToOne
    Client owner;

    @ManyToOne
    BankBranch branch;

    @OneToMany
    List<Transaction> transactions;
}
