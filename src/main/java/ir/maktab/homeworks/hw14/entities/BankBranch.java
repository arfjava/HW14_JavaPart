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
public class BankBranch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToMany
    List<Account> accounts;

    @OneToMany
    List<Employee> employees;

    @OneToOne
    Manager manager;
}
