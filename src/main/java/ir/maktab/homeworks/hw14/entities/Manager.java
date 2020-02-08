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
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String managerNumber;

    @OneToOne(cascade = CascadeType.ALL)
    private PersonalInfo personalInfo;

    @OneToOne
    private BankBranch branch;

}
