package ir.maktab.homeworks.hw14.actions.abstraction;

import com.fasterxml.jackson.core.JsonProcessingException;
import ir.maktab.homeworks.hw14.entities.Transaction;

import java.text.ParseException;

public interface TransactionActions {
    Transaction add();
    String loadAccountTransactionsAsJson() throws JsonProcessingException, ParseException;
}
