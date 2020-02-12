package ir.maktab.homeworks.hw14.utilities;

import ir.maktab.homeworks.hw14.entities.Customer;
import ir.maktab.homeworks.hw14.repositories.BankBranchRepository;
import ir.maktab.homeworks.hw14.repositories.CardRepository;
import ir.maktab.homeworks.hw14.repositories.CustomerRepository;

import java.util.*;
import java.util.stream.Collectors;

public class AutoGenerateNumbers {
    public static String generateUniqueCardNumber(){

        String result = "";

        Random random = new Random();
        do {
            result = "";
            while (result.length() != 19){
                int intVal = random.nextInt(10000);
                String strVal = String.valueOf(intVal);
                while (strVal.length() < 4)
                    strVal = "0" + strVal;

                if (!result.isEmpty())
                    result += "-";

                result += strVal;
            }

        }while (CardRepository.getInstance().isCardNumberExisting(result));

        return  result;
    }

    public static String generateNewCustomerNumber(Long branchId){
        String result;

        List<Integer> customersNumbersInBranchList =
        BankBranchRepository.getInstance().findById(branchId).getCustomers()
                .stream()
                .map(customer -> customer.getCustomerNumber().split("-")[customer.getCustomerNumber().split("-").length - 1])
                .map(str -> Integer.parseInt(str))
                .sorted()
                .collect(Collectors.toList());

        Integer newCustomerNumberInBranch = customersNumbersInBranchList.get(customersNumbersInBranchList.size() - 1) + 1;

        result = String.valueOf(branchId) + "-" + newCustomerNumberInBranch;

        return result;
    }

    public static String generateNewAccountNumber(String customerNumber){
        String result;

        Customer customer = CustomerRepository.getInstance().findByCustomerNumber(customerNumber);
        Integer newAccountNumberForCustomer;

        if (customer.getAccounts() != null && customer.getAccounts().size() > 0) {
            List<Integer> accountsNumbersForCustomer =
                    customer.getAccounts()
                            .stream()
                            .map(account -> account.getAccountNumber().split("-")[account.getAccountNumber().split("-").length - 1])
                            .map(str -> Integer.parseInt(str))
                            .sorted()
                            .collect(Collectors.toList());
            newAccountNumberForCustomer = accountsNumbersForCustomer.get(accountsNumbersForCustomer.size() - 1) + 1;
        }
        else
            newAccountNumberForCustomer = 1;

        result = customerNumber + "-" + newAccountNumberForCustomer;

        return result;
    }

    public static Integer generateRandomCvv2(){
        return new Random().nextInt(8889) + 1111;
    }

    public static String randomPassword(){
        String password = String.valueOf(new Random().nextInt(10000));
        while (password.length() < 4)
            password = "0" + password;
        return password;
    }

    public static String generateExpirationDate(){
        String currentDate = MyDate.getMyFormat().format(new Date());
        Long currentYear = Long.parseLong(currentDate.substring(0,4));
        String expirationYear = String.valueOf(currentYear + 5);
        String expirationDate = currentDate.replace(String.valueOf(currentYear), expirationYear);
        return expirationDate;
    }
}
