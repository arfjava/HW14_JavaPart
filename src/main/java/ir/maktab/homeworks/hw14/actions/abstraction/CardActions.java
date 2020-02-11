package ir.maktab.homeworks.hw14.actions.abstraction;

import com.fasterxml.jackson.core.JsonProcessingException;
import ir.maktab.homeworks.hw14.entities.Card;

public interface CardActions {
    Card add() throws JsonProcessingException;
    void remove();
    Card disable();
    String loadAsJson() throws JsonProcessingException;
    void passwordManager();
    void transferByCard();
}
