package ir.maktab.homeworks.hw14.summary_classes;


import lombok.Data;

@Data
public class AccountSummary {
    private Long id;
    private String accountNumber;
    private Boolean isActive;
    private String card;
    private Long balance;
}
