package ir.maktab.homeworks.hw14.inputs;

import ir.maktab.homeworks.hw14.repositories.AccountRepository;
import ir.maktab.homeworks.hw14.repositories.BankBranchRepository;
import ir.maktab.homeworks.hw14.repositories.CardRepository;
import ir.maktab.homeworks.hw14.repositories.CustomerRepository;
import ir.maktab.homeworks.hw14.utilities.IsNumeric;
import ir.maktab.homeworks.hw14.utilities.TransactionType;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Input {
    private static Scanner scanner = new Scanner(System.in);

    public static String country(){

        System.out.print("Country: ");
        String country = scanner.nextLine();
        // TODO: add validation if needed

        return country;
    }
    public static String city(){

        System.out.print("City: ");
        String city = scanner.nextLine();
        // TODO: add validation if needed

        return city;
    }
    public static String street(){

        System.out.print("Street: ");
        String street = scanner.nextLine();
        // TODO: add validation if needed

        return street;
    }
    public static String zipCode(){

        String zipCode;
        boolean zipCodeValidation;
        do {
            System.out.print("ZipCode: ");
            zipCode = scanner.nextLine();

            zipCodeValidation = IsNumeric.execute(zipCode) && zipCode.length() == 10;

            if (!zipCodeValidation)
                System.out.println("Invalid ZipCode!");
        }while (!zipCodeValidation);

        return zipCode;
    }
    public static String name(){
        System.out.print("Name: ");
        String name = scanner.nextLine();
        // TODO: add validation if needed

        return name;
    }
    public static String family(){
        System.out.print("Family: ");
        String family = scanner.nextLine();
        // TODO: add validation if needed
        return family;
    }
    public static String nationalCode(){
        String nationalCode;
        boolean nationalCodeValidation;
        do {
            System.out.print("National Code: ");
            nationalCode = scanner.nextLine();
            String finalNationalCode = nationalCode;
            nationalCodeValidation = IsNumeric.execute(nationalCode)
                    && nationalCode.length() == 10;

            if (!nationalCodeValidation)
                System.out.println("Entered National Code is Invalid!");
        }while (!nationalCodeValidation);

        return nationalCode;
    }
    public static String accountNumber(){
        String result;

        String accountNumber;
        boolean accountNumberValidation;
        do {
            System.out.println("Account Number: ");
            accountNumber = scanner.nextLine();

            accountNumberValidation = AccountRepository.getInstance().isAccountNumberExisting(accountNumber);
            if (!accountNumberValidation)
                System.out.println("Invalid Account Number!");
        }while (!accountNumberValidation);

        result = accountNumber;

        return result;
    }
    public static String customerNumber(){
        String result;

        String customerNumber;
        boolean customerNumberValidation;
        do {
            System.out.print("Customer Number: ");
            customerNumber = scanner.nextLine();

            customerNumberValidation = CustomerRepository.getInstance().isCustomerNumberExisting(customerNumber);
            if (!customerNumberValidation)
                System.out.println("Invalid Customer Number!");

        }while (!customerNumberValidation);

        result = customerNumber;

        return result;
    }
    public static String cardNumber(){

        String cardNumber;
        boolean cardNumberValidation;
        do {
            System.out.print("Card Number: ");
            cardNumber = scanner.nextLine();

            cardNumberValidation =  cardNumber.length() == 19
                    && Arrays.stream(cardNumber.split("-")).filter(s -> (IsNumeric.execute(s) && s.length() == 4))
                    .collect(Collectors.toList()).size() == 4
                    && CardRepository.getInstance().isCardNumberExisting(cardNumber);

            if (!cardNumberValidation)
                System.out.println("Invalid Card Number!");
        }while (!cardNumberValidation);

        return cardNumber;
    }
    public static Long branchId(){
        String choiceBranchId;
        boolean choiceBranchValidation;
        do {
            System.out.println("Select Branch:");
            BankBranchRepository.getInstance().findAll()
                    .stream()
                    .map(bankBranch -> bankBranch.getId() + ". Bank Branch " + bankBranch.getId())
                    .forEach(System.out::println);
            choiceBranchId = scanner.nextLine();

            choiceBranchValidation = IsNumeric.execute(choiceBranchId)
                    && BankBranchRepository.getInstance().isExisting(Long.parseLong(choiceBranchId));
            if (!choiceBranchValidation)
                System.out.println("Invalid Input!");
        }while (!choiceBranchValidation);

        return Long.parseLong(choiceBranchId);
    }
    public static TransactionType transactionType(){
        TransactionType result;

        String choice;
        boolean choiceValidation;
        do {
            System.out.println("Choose Transaction Type:\n1. Deposit\n2. Withdraw");
            choice = scanner.nextLine();

            choiceValidation = choice.equals("1") || choice.equals("2");

            if (!choiceValidation)
                System.out.println("Invalid Input!");
        }while (!choiceValidation);


        if (choice.equals("1"))
            result = TransactionType.DEPOSIT;
        else
            result = TransactionType.WITHDRAW;

        return result;
    }
    public static String date(){

        String date;
        boolean dateValidation;
        do {
            System.out.print("From Date(yyyy-mm-dd): ");
            date = scanner.nextLine();

            String[] splitDateInput = date.split("-");
            dateValidation = splitDateInput.length == 3
                    && splitDateInput[0].length() == 4 && IsNumeric.execute(splitDateInput[0])
                    && splitDateInput[1].length() == 2 && IsNumeric.execute(splitDateInput[1])
                    && splitDateInput[2].length() == 2 && IsNumeric.execute(splitDateInput[2]);
            if (!dateValidation)
                System.out.println("Invalid Input!");

        }while (!dateValidation);

        return date;
    }








}
