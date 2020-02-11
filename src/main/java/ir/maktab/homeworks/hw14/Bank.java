package ir.maktab.homeworks.hw14;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import ir.maktab.homeworks.hw14.actions.abstraction.AccountActions;
import ir.maktab.homeworks.hw14.actions.abstraction.CardActions;
import ir.maktab.homeworks.hw14.actions.abstraction.CustomerActions;
import ir.maktab.homeworks.hw14.actions.abstraction.TransactionActions;
import ir.maktab.homeworks.hw14.actions.impl.AccountActionsImpl;
import ir.maktab.homeworks.hw14.actions.impl.CardActionsImpl;
import ir.maktab.homeworks.hw14.actions.impl.CustomerActionsImpl;
import ir.maktab.homeworks.hw14.actions.impl.TransactionActionsImpl;
import ir.maktab.homeworks.hw14.entities.*;
import ir.maktab.homeworks.hw14.functions.Functions;
import ir.maktab.homeworks.hw14.menu.Menu;
import ir.maktab.homeworks.hw14.repositories.AccountRepository;
import ir.maktab.homeworks.hw14.repositories.BankBranchRepository;
import ir.maktab.homeworks.hw14.repositories.CardRepository;
import ir.maktab.homeworks.hw14.repositories.CustomerRepository;
import ir.maktab.homeworks.hw14.summary_classes.AccountSummary;
import ir.maktab.homeworks.hw14.summary_classes.TransactionSummary;
import ir.maktab.homeworks.hw14.utilities.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Bank {
    public static void main(String[] args) throws JsonProcessingException, ParseException {


        Scanner scanner = new Scanner(System.in);


        Initialization.execute();

        AccountActions accountActions = new AccountActionsImpl();
        CardActions cardActions = new CardActionsImpl();
        CustomerActions customerActions = new CustomerActionsImpl();
        TransactionActions transactionActions = new TransactionActionsImpl();

        String command = "";

        while (!command.equalsIgnoreCase("14")) {

            System.out.println("command: (Press m to See Menu)");
            command = scanner.nextLine();


            if (command.equalsIgnoreCase("m")){
                Menu.display();
            }
             if (command.equalsIgnoreCase("1")){
                customerActions.add();
            }
            else if (command.equalsIgnoreCase("2")) {
                accountActions.add();
            }
            else if (command.equalsIgnoreCase("3")) {
                cardActions.add();
            }
            else if (command.equalsIgnoreCase("4")) {
                cardActions.passwordManager();
            }
            else if (command.equalsIgnoreCase("5")) {
                transactionActions.add();
            }
            else if (command.equalsIgnoreCase("6")) {
                cardActions.transferByCard();
            }
            else if (command.equalsIgnoreCase("7")) {
                System.out.println(accountActions.loadAsJson());
            }
             else if (command.equalsIgnoreCase("8")) {
                 System.out.println(accountActions.loadCustomerAccountsAsJson());
             }
            else if (command.equalsIgnoreCase("9")) {
                System.out.println(transactionActions.loadAccountTransactionsAsJson());
            }
            else if (command.equalsIgnoreCase("10")) {
                System.out.println(cardActions.loadAsJson());
            }
            else if (command.equalsIgnoreCase("11")) {
                accountActions.close();
            }
            else if (command.equalsIgnoreCase("12")) {
                cardActions.remove();
            }
            else if (command.equalsIgnoreCase("13")) {
                cardActions.disable();
            }
            else if (command.equalsIgnoreCase("14")){
                System.out.println("Bye!");
            }
            else {
                System.out.println("Invalid Command!");
            }

        }

    }

}




