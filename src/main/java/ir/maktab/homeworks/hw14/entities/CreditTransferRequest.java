package ir.maktab.homeworks.hw14.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CreditTransferRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long sourceCardId;
    Long destinationCardId;
    Long amount;
    String description;

    @ManyToOne
    Card card;
}
