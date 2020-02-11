package ir.maktab.homeworks.hw14.actions.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.maktab.homeworks.hw14.actions.abstraction.AccountActions;
import ir.maktab.homeworks.hw14.entities.Account;
import ir.maktab.homeworks.hw14.entities.Customer;
import ir.maktab.homeworks.hw14.entities.Transaction;
import ir.maktab.homeworks.hw14.functions.Functions;
import ir.maktab.homeworks.hw14.inputs.Input;
import ir.maktab.homeworks.hw14.repositories.AccountRepository;
import ir.maktab.homeworks.hw14.repositories.CustomerRepository;
import ir.maktab.homeworks.hw14.summary_classes.AccountSummary;
import ir.maktab.homeworks.hw14.summary_classes.TransactionSummary;
import ir.maktab.homeworks.hw14.utilities.AutoGenerateNumbers;
import ir.maktab.homeworks.hw14.utilities.JsonFactory;
import ir.maktab.homeworks.hw14.utilities.MyDate;
import ir.maktab.homeworks.hw14.utilities.TransactionType;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AccountActionsImpl implements AccountActions {

    @Override
    public Account add() throws JsonProcessingException {
        Account result;

        String customerNumber = Input.customerNumber();

        Customer customer = CustomerRepository.getInstance().findByCustomerNumber(customerNumber);

        String accountNumber = AutoGenerateNumbers.generateNewAccountNumber(customerNumber);


        ObjectMapper mapper = new ObjectMapper();

        Transaction transaction = mapper.readValue(JsonFactory.jsonTransaction
                        (TransactionType.DEPOSIT, MyDate.getMyFormat().format(new Date()),
                                100000L, true, "Account Opening")
                , new TypeReference<Transaction>() {});

        Account account = mapper.readValue(JsonFactory.jsonAccount(accountNumber, true, 100000L)
                , new TypeReference<Account>() {});

        customer.getAccounts().add(account);
        account.setCustomer(customer);
        account.getTransactions().add(transaction);
        transaction.setAccount(account);

        CustomerRepository.getInstance().update(customer);

        System.out.println("Account Number: " +accountNumber);

        result = AccountRepository.getInstance().findByAccountNumber(accountNumber);

        return result;
    }


    @Override
    public Account close() {
        Account result;

        String accountNumber = Input.accountNumber();

        Account account = AccountRepository.getInstance().findByAccountNumber(accountNumber);

        if (account.getIsActive()) {
            if (account.getCard() != null)
                account.getCard().setIsActive(false);
            Long balance = account.getBalance();
            account.getTransactions().add(new Transaction(null, TransactionType.WITHDRAW, MyDate.getMyFormat().format(new Date())
                    , balance, true, "Closing Account", account));
            account.setBalance(0L);
            account.setIsActive(false);
            result = AccountRepository.getInstance().update(account);
        }
        else {
            System.out.println("Requested Account is Disabled Already!");
            result = null;
        }
        return result;
    }


    @Override
    public String loadAsJson() throws JsonProcessingException {

        String accountNumber = Input.accountNumber();

        Account account = AccountRepository.getInstance().findByAccountNumber(accountNumber);

        AccountSummary accountSummary = Stream.of(account).map(Functions.accountSummaryFunction)
                .collect(Collectors.toList()).get(0);

        ObjectMapper mapper = new ObjectMapper();

        String str = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(accountSummary);

        return str;
    }

    @Override
    public String loadCustomerAccountsAsJson() throws JsonProcessingException {
        String result;

        String customerNumber = Input.customerNumber();
        Customer customer = CustomerRepository.getInstance().findByCustomerNumber(customerNumber);

        List<AccountSummary> accountSummaryList = customer.getAccounts().stream().map(Functions.accountSummaryFunction)
                .collect(Collectors.toList());

        ObjectMapper mapper = new ObjectMapper();
        result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(accountSummaryList);

        return result;
    }

}
