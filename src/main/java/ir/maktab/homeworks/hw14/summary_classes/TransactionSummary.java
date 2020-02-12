package ir.maktab.homeworks.hw14.summary_classes;

import ir.maktab.homeworks.hw14.utilities.TransactionType;
import lombok.Data;

@Data
public class TransactionSummary {

    private Long id;
    private TransactionType transactionType;
    String date;
    Long amount;
    Boolean isSuccessful;
    private String description;
    private String accountInfo;
}
