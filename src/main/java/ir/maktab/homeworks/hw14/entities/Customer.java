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
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, unique = true)
    String customerNumber;

    @OneToOne(cascade = CascadeType.ALL)
    PersonalInfo personalInfo;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    List<Account> accounts;


    @ManyToOne
    BankBranch branch;
}