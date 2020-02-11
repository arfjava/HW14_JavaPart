package ir.maktab.homeworks.hw14.functions;

import ir.maktab.homeworks.hw14.entities.Account;
import ir.maktab.homeworks.hw14.entities.Transaction;
import ir.maktab.homeworks.hw14.summary_classes.AccountSummary;
import ir.maktab.homeworks.hw14.summary_classes.TransactionSummary;

import java.util.function.Function;

public class Functions {

    public static Function<Account, AccountSummary> accountSummaryFunction = account -> {
        AccountSummary accountSummary = new AccountSummary();
        accountSummary.setId(account.getId());
        accountSummary.setAccountNumber(account.getAccountNumber());
        accountSummary.setIsActive(account.getIsActive());
        accountSummary.setCard(account.getCard() != null? account.getCard().getCardNumber(): "null");
        accountSummary.setBalance(account.getBalance());
        return accountSummary;
    };

    public static Function<Transaction, TransactionSummary> transactionSummaryFunction = transaction -> {
        TransactionSummary transactionSummary = new TransactionSummary();
        transactionSummary.setId(transaction.getId());
        transactionSummary.setTransactionType(transaction.getTransactionType());
        transactionSummary.setAmount(transaction.getAmount());
        transactionSummary.setIsSuccessful(transaction.getIsSuccessful());
        transactionSummary.setDate(transaction.getDate());
        transactionSummary.setDescription(transaction.getDescription());
        transactionSummary.setAccountInfo(transaction.getAccount().getAccountNumber() + " "
        + transaction.getAccount().getCustomer().getPersonalInfo().getName() + " "
        +transaction.getAccount().getCustomer().getPersonalInfo().getFamily());

        return transactionSummary;
    };
}
