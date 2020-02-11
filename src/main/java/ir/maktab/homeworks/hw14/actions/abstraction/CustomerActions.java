package ir.maktab.homeworks.hw14.actions.abstraction;

import com.fasterxml.jackson.core.JsonProcessingException;
import ir.maktab.homeworks.hw14.entities.Customer;

public interface CustomerActions {
    Customer add() throws JsonProcessingException;
}
