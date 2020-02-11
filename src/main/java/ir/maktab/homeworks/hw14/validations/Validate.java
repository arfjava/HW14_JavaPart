package ir.maktab.homeworks.hw14.validations;

import ir.maktab.homeworks.hw14.repositories.CardRepository;
import ir.maktab.homeworks.hw14.utilities.IsNumeric;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Validate {
    public static boolean cardNumber(String cardNumber){
        boolean cardNumberValidation =  cardNumber.length() == 19
                && Arrays.stream(cardNumber.split("-")).filter(s -> (IsNumeric.execute(s) && s.length() == 4))
                .collect(Collectors.toList()).size() == 4
                && CardRepository.getInstance().isCardNumberExisting(cardNumber);
        return cardNumberValidation;
    }
}
