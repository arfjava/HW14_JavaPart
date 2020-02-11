package ir.maktab.homeworks.hw14.actions.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.maktab.homeworks.hw14.actions.abstraction.CardActions;
import ir.maktab.homeworks.hw14.entities.Account;
import ir.maktab.homeworks.hw14.entities.Card;
import ir.maktab.homeworks.hw14.entities.CardPasswordInfo;
import ir.maktab.homeworks.hw14.entities.Transaction;
import ir.maktab.homeworks.hw14.inputs.Input;
import ir.maktab.homeworks.hw14.repositories.AccountRepository;
import ir.maktab.homeworks.hw14.repositories.CardRepository;
import ir.maktab.homeworks.hw14.utilities.*;
import ir.maktab.homeworks.hw14.validations.Validate;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class CardActionsImpl implements CardActions {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public Card add() throws JsonProcessingException {
        Card result;

        String accountNumber = Input.accountNumber();
        Account account = AccountRepository.getInstance().findByAccountNumber(accountNumber);

        if (account.getIsActive()) {

            if (account.getCard() == null || !account.getCard().getIsActive()) {

                if (account.getCard() != null)
                    CardRepository.getInstance().remove(account.getCard());



                String cardNumber = AutoGenerateNumbers.generateUniqueCardNumber();

                String expirationDate = AutoGenerateNumbers.generateExpirationDate();

                Integer cvv2 = AutoGenerateNumbers.generateRandomCvv2();

                String password = AutoGenerateNumbers.randomPassword();


                ObjectMapper mapper = new ObjectMapper();

                Card card = mapper.readValue(JsonFactory.jsonCard(cardNumber, true)
                        , new TypeReference<Card>() {
                        });
                CardPasswordInfo cardPasswordInfo = mapper.readValue(JsonFactory.jasonCardPasswordInfo
                        (expirationDate, String.valueOf(cvv2), password, null), new TypeReference<CardPasswordInfo>() {
                });

                card.setCardPasswordInfo(cardPasswordInfo);
                account.setCard(card);

                AccountRepository.getInstance().update(account);

                System.out.println("Card Number: " + cardNumber);
                System.out.println("Password: " + password);

                result = CardRepository.getInstance().findByCardNumber(cardNumber);
            }
            else {
                System.out.println("Requested Account has An Active Card Already!");
                result = null;
            }
        }
        else {
            System.out.println("Requested Account is Disabled!");
            result = null;
        }
        return result;
    }

    @Override
    public void remove() {

        String cardNumber = Input.cardNumber();
        Card card = CardRepository.getInstance().findByCardNumber(cardNumber);


        Account account = CardRepository.getInstance().findAccountByCardNumber(cardNumber);
        account.setCard(null);
        AccountRepository.getInstance().update(account);
        CardRepository.getInstance().remove(card);
    }

    @Override
    public Card disable() {
        Card result;

        String cardNumber = Input.cardNumber();

        Card card = CardRepository.getInstance().findByCardNumber(cardNumber);
        if (card.getIsActive()) {
            card.setIsActive(false);
            result = CardRepository.getInstance().update(card);
        }
        else {
            System.out.println("Requested Card Is Disabled Already!");
            result = null;
        }

        return result;
    }

    @Override
    public String loadAsJson() throws JsonProcessingException {

        String cardNumber = Input.cardNumber();

        Card card = CardRepository.getInstance().findByCardNumber(cardNumber);

        ObjectMapper mapper = new ObjectMapper();

        String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(card);

        return jsonResult;
    }

    @Override
    public void passwordManager() {

        String cardNumber = Input.cardNumber();
        Card card = CardRepository.getInstance().findByCardNumber(cardNumber);

        if (card.getIsActive()) {
            System.out.print("Password: ");
            String password = scanner.nextLine();

            if (card.getCardPasswordInfo().getPassword().equals(password)) {
                if (card.getCardPasswordInfo().getSecondPassword() == null) {
                    System.out.println("You Have not Set Your Second Password!");

                    String secondPassword;
                    boolean secondPassValidation;
                    do {
                        System.out.println("Second Password: ");
                        secondPassword = scanner.nextLine();

                        secondPassValidation = IsNumeric.execute(secondPassword) && secondPassword.length() == 6;

                        if (!secondPassValidation)
                            System.out.println("Invalid Second Password!");
                    } while (!secondPassValidation);


                    card.getCardPasswordInfo().setSecondPassword(secondPassword);
                    CardRepository.getInstance().update(card);
                } else {
                    System.out.println("You Have Set Second Password Already!");
                    System.out.println("1. change second password\n2. delete second password");
                    String choice = scanner.nextLine();

                    if (choice.equals("1")) {

                        String secondPassword;
                        boolean secondPassValidation;
                        do {
                            System.out.println("New Second Password: ");
                            secondPassword = scanner.nextLine();

                            secondPassValidation = IsNumeric.execute(secondPassword) && secondPassword.length() == 6;

                            if (!secondPassValidation)
                                System.out.println("Invalid Second Password!");
                        } while (!secondPassValidation);

                        card.getCardPasswordInfo().setSecondPassword(secondPassword);
                        CardRepository.getInstance().update(card);
                    } else if (choice.equals("2")) {
                        card.getCardPasswordInfo().setSecondPassword(null);
                        CardRepository.getInstance().update(card);
                    } else {
                        System.out.println("invalid input!");
                    }
                }
            } else {
                System.out.println("Wrong Password!");
                card.getCardPasswordInfo().setWrongPasswordCounter(card.getCardPasswordInfo().getWrongPasswordCounter() + 1);
                if (card.getCardPasswordInfo().getWrongPasswordCounter() == 3) {
                    System.out.println("Wrong Pass for 3 Times! Your Card Will be disabled!");
                    card.getCardPasswordInfo().setWrongPasswordCounter(0);
                    card.setIsActive(false);
                }
                CardRepository.getInstance().update(card);
            }
        }
        else {
            System.out.println("Requested Card is Disabled!");
        }
    }

    @Override
    public void transferByCard() {


        System.out.println("Origin Card Number: ");
        String originCardNumber = scanner.nextLine();

        System.out.println("Destination Card Number: ");
        String destinationCardNumber = scanner.nextLine();

        System.out.println("Amount: ");
        String amount = scanner.nextLine();

        if (Validate.cardNumber(originCardNumber) && Validate.cardNumber(destinationCardNumber)
                && CardRepository.getInstance().findByCardNumber(originCardNumber).getIsActive()
                && CardRepository.getInstance().findByCardNumber(destinationCardNumber).getIsActive()
                && IsNumeric.execute(amount) && Long.parseLong(amount) >= 0) {

            Account originAccount = CardRepository.getInstance().findAccountByCardNumber(originCardNumber);
            Account destinationAccount = CardRepository.getInstance().findAccountByCardNumber(destinationCardNumber);


            Transaction originTransaction = new Transaction(null, TransactionType.WITHDRAW, MyDate.getMyFormat().format(new Date())
                    , Long.parseLong(amount), null, "from " + originCardNumber + " to " + destinationCardNumber
                    , originAccount);

            Transaction destinationTransaction = new Transaction(null, TransactionType.DEPOSIT, MyDate.getMyFormat().format(new Date())
                    , Long.parseLong(amount), null, "from " + originCardNumber + " to " + destinationCardNumber
                    , destinationAccount);


            System.out.println("Second Password: ");
            String secondPassword = scanner.nextLine();

            System.out.println("CVV2: ");
            String cvv2 = scanner.nextLine();

            System.out.println("Expiration Date(yyyy-mm-dd): ");
            String expirationDate = scanner.nextLine();

            Card originCard = CardRepository.getInstance().findByCardNumber(originCardNumber);

            if (secondPassword.equals(originCard.getCardPasswordInfo().getSecondPassword())
                    && expirationDate.equals(originCard.getCardPasswordInfo().getExpirationDate().substring(0,10))
                    && IsNumeric.execute(cvv2)
                    && Integer.parseInt(cvv2) == originCard.getCardPasswordInfo().getCvv2()
                    && CardRepository.getInstance().findAccountByCardNumber(originCardNumber).getBalance() >= Long.parseLong(amount) + 5000L) {

                originTransaction.setIsSuccessful(true);
                destinationTransaction.setIsSuccessful(true);

                originAccount.setBalance(originAccount.getBalance() - Long.parseLong(amount) - 5000L);
                destinationAccount.setBalance(destinationAccount.getBalance() + Long.parseLong(amount));

            } else {

                if (!secondPassword.equals(originCard.getCardPasswordInfo().getSecondPassword())) {
                    Integer wrongPassCount = originAccount.getCard().getCardPasswordInfo().getWrongPasswordCounter();
                    originAccount.getCard().getCardPasswordInfo().setWrongPasswordCounter(wrongPassCount + 1);
                    if (originAccount.getCard().getCardPasswordInfo().getWrongPasswordCounter() == 3) {
                        originAccount.getCard().setIsActive(false);
                        originAccount.getCard().getCardPasswordInfo().setWrongPasswordCounter(0);
                        System.out.println("You Input Wrong Password 3 Times! Your Card is Disabled!");
                    }
                }
                else if (CardRepository.getInstance().findAccountByCardNumber(originCardNumber).getBalance() < Long.parseLong(amount) + 5000L)
                    System.out.println("There is not Enough Amount in Origin Account!");

                System.out.println("invalid Input!");
                System.out.println("Transaction Failed!");
                originTransaction.setIsSuccessful(false);
                destinationTransaction.setIsSuccessful(false);
            }

            originAccount.getTransactions().add(originTransaction);
            destinationAccount.getTransactions().add(destinationTransaction);

            AccountRepository.getInstance().update(originAccount);
            AccountRepository.getInstance().update(destinationAccount);
        } else {
            System.out.println("invalid input");
        }
    }





}
