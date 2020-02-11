package ir.maktab.homeworks.hw14.actions.abstraction;

import com.fasterxml.jackson.core.JsonProcessingException;
import ir.maktab.homeworks.hw14.entities.Account;

public interface AccountActions {
    Account add() throws JsonProcessingException;
    Account close();
    String loadAsJson() throws JsonProcessingException;
    String loadCustomerAccountsAsJson() throws JsonProcessingException;
}
