package ir.maktab.homeworks.hw14.actions.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.maktab.homeworks.hw14.actions.abstraction.TransactionActions;
import ir.maktab.homeworks.hw14.entities.Account;
import ir.maktab.homeworks.hw14.entities.Transaction;
import ir.maktab.homeworks.hw14.functions.Functions;
import ir.maktab.homeworks.hw14.inputs.Input;
import ir.maktab.homeworks.hw14.repositories.AccountRepository;
import ir.maktab.homeworks.hw14.summary_classes.TransactionSummary;
import ir.maktab.homeworks.hw14.utilities.IsNumeric;
import ir.maktab.homeworks.hw14.utilities.MyDate;
import ir.maktab.homeworks.hw14.utilities.TransactionType;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TransactionActionsImpl implements TransactionActions {
    Scanner scanner = new Scanner(System.in);

    @Override
    public Transaction add() {
        Transaction result;

        String accountNumber = Input.accountNumber();
        Account account = AccountRepository.getInstance().findByAccountNumber(accountNumber);

        if (account.getIsActive()){

            TransactionType transactionType = Input.transactionType();

            String amount;
            boolean amountValidation;
            do {
                System.out.println("Amount");
                amount = scanner.nextLine();

                amountValidation = IsNumeric.execute(amount);
                if (!amountValidation)
                    System.out.println("Invalid Input!");
            }while (!amountValidation);


            Transaction transaction = new Transaction(null, transactionType, MyDate.getMyFormat().format(new Date())
                    , Long.parseLong(amount), true, transactionType.toString(), account);
            if (transactionType.equals(TransactionType.WITHDRAW) && Long.parseLong(amount) > account.getBalance()) {
                transaction.setIsSuccessful(false);
                System.out.println("There is not Enough Balance in Origing Account!");
            }
            else if (transactionType.equals(TransactionType.DEPOSIT))
                account.setBalance(account.getBalance() + Long.parseLong(amount));
            else if (transactionType.equals(TransactionType.WITHDRAW))
                account.setBalance(account.getBalance() - Long.parseLong(amount));
            account.getTransactions().add(transaction);

            Account updatedAccount = AccountRepository.getInstance().update(account);

            result = updatedAccount.getTransactions().get(updatedAccount.getTransactions().size() - 1);
        }
        else {
            System.out.println("Requested Account is Disabled!");
            result = null;
        }


        return result;
    }

    @Override
    public String loadAccountTransactionsAsJson() throws JsonProcessingException, ParseException {
        String result;

        String accountNumber = Input.accountNumber();

        Date fromDate = MyDate.getMyFormat().parse(Input.date() + " 00:00:00") ;


        if (new Date().compareTo(fromDate) > 0) {
            Account account = AccountRepository.getInstance().findByAccountNumber(accountNumber);

            ObjectMapper mapper = new ObjectMapper();

            List<TransactionSummary> transactions = account.getTransactions().stream()
                    .filter(transaction -> {
                        try {
                            return MyDate.getMyFormat().parse(transaction.getDate()).compareTo(fromDate) > 0;
                        } catch (ParseException e) {
                            e.printStackTrace();
                            return true; // TODO: 2/11/2020
                        }
                    })
                    .map(Functions.transactionSummaryFunction).collect(Collectors.toList());
            result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(transactions);
        }
        else {
            result = "Invalid Date!";
        }
        return result;
    }
}
