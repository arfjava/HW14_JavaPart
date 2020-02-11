package ir.maktab.homeworks.hw14.utilities;

import ir.maktab.homeworks.hw14.entities.Account;

import java.util.Arrays;
import java.util.List;

public class JsonFactory {


//    public static String jsonAddress(String country, String city, String street, String zipCode){
//
//        // TODO: 2/9/2020 validation
//
//        String jsonCountry = "\"country\":\"" + country + "\"";
//        String jsonCity = "\"city\":\"" + city + "\"";
//        String jsonStreet = "\"street\":\"" + street + "\"";
//        String jsonZipCode = "\"zipCode\":\"" + zipCode + "\"";
//
//        String result = "{"
//                + jsonCountry + ", "
//                + jsonCity + ", "
//                + jsonStreet + ", "
//                +jsonZipCode +
//                "}";
//
//        return result;
//    }
//
//    public static String jsonPersonalInfo(String name, String family, String nationalCode, String jsonAddress){
//        // TODO: 2/9/2020 validation
//
//        String jsonName = "\"name\":\"" + name + "\"";
//        String jsonFamily = "\"family\":\"" + family + "\"";
//        String jsonNationalCode = "\"nationalCode\":\"" + nationalCode + "\"";
//        String preparedJasonAddress = "\"address\":" + jsonAddress;
//
//        String result = "{"
//                + jsonName + ", "
//                + jsonFamily + ", "
//                + jsonNationalCode + ", "
//                + preparedJasonAddress +
//                "}";
//        return result;
//    }
//
//    public static String jasonCardPasswordInfo(String expirationDate, String cvv2, String password, String secondPassword){
//        // TODO: 2/9/2020 validations
//
//        String jsonExpirationDate = "\"expirationDate\":\"" + expirationDate + "\"";
//        String jsonCvv2= "\"cvv2\":\"" + cvv2 + "\"";
//        String jsonPassword= "\"password\":\"" + password + "\"";
//        String jsonSecondPassword= "\"secondPassword\":\"" + secondPassword + "\"";
//
//        String result = "{"
//                + jsonExpirationDate + ", "
//                + jsonCvv2 + ", "
//                + jsonPassword + ", "
//                + jsonSecondPassword +
//                "}";
//
//        return result;
//    }
//
//    public static String jsonCard(String cardNumber, Boolean isActive, String jsonCardPasswordInfo){
//        // TODO: 2/9/2020 validations
//
//        String jsonCardNumber = "\"cardNumber\":\"" + cardNumber + "\"";
//        String jsonIsActive= "\"isActive\":" + isActive ;
//        String preparedJsonCardPasswordInfo = "\"cardPasswordInfo\":" + jsonCardPasswordInfo ;
//
//        String result = "{"
//                + jsonCardNumber + ", "
//                + jsonIsActive + ", "
//                + preparedJsonCardPasswordInfo +
//                "}";
//
//        return result;
//    }
//
//    public static String jsonTransaction(TransactionType transactionType, String date, Long amount, Boolean isSuccessful, String description){
//        // TODO: 2/9/2020 validations
//
//        String jsonTransactionType = "\"transactionType\":\"" + transactionType + "\"";
//        String jsonDate= "\"date\":\"" + date + "\"";
//        String jsonAmount= "\"amount\":" + amount + "";
//        String jsonIsSuccessful= "\"isSuccessful\":" + isSuccessful + "";
//        String jsonDescription= "\"description\":\"" + description + "\"";
//
//        String result = "{"
//                + jsonTransactionType + ", "
//                + jsonDate + ", "
//                + jsonAmount + ", "
//                + jsonIsSuccessful + ", "
//                + jsonDescription +
//                "}";
//
//        return result;
//    }
//
//    public static String jsonAccount(String accountNumber, Boolean isActive, Long balance, String jsonCard, String[] jsonTransactions){
//        // TODO: 2/9/2020 validations
//
//        String jsonAccountNumber = "\"accountNumber\":\"" + accountNumber + "\"";
//        String jsonIsActive= "\"isActive\":" + isActive + "";
//        String jsonBalance= "\"balance\":" + balance + "";
//        String preparedJasonCard = "\"card\":" + jsonCard + "";
//        String jsonListOfJsonTransactions = "\"transactions\":" + Arrays.asList(jsonTransactions).toString();
//
//
//        String result = "{"
//                + jsonAccountNumber + ", "
//                + jsonIsActive + ", "
//                + jsonBalance + ", "
//                + preparedJasonCard + ", "
//                + jsonListOfJsonTransactions +
//                "}";
//
//        return result;
//    }
//
//    public static String jsonCustomer(String customerNumber, String jsonPersonalInfo, String[] jsonAccounts){
//        // TODO: 2/9/2020 validations
//
//        String jsonCustomerNumber = "\"customerNumber\":\"" + customerNumber + "\"";
//        String preparedJsonPersonalInfo = "\"personalInfo\":" + jsonPersonalInfo + "";
//        String jsonListOfJsonAccounts = "\"accounts\":" + Arrays.asList(jsonAccounts).toString();
//
//        String result = "{"
//                + jsonCustomerNumber + ", "
//                + preparedJsonPersonalInfo + ", "
//                + jsonListOfJsonAccounts +
//                "}";
//
//        return result;
//    }



    public static String jsonAddress(String country, String city, String street, String zipCode){

        // TODO: 2/9/2020 validation

        String jsonCountry = "\"country\":\"" + country + "\"";
        String jsonCity = "\"city\":\"" + city + "\"";
        String jsonStreet = "\"street\":\"" + street + "\"";
        String jsonZipCode = "\"zipCode\":\"" + zipCode + "\"";

        String result = "{"
                + jsonCountry + ", "
                + jsonCity + ", "
                + jsonStreet + ", "
                +jsonZipCode +
                "}";

        return result;
    }

    public static String jsonPersonalInfo(String name, String family, String nationalCode){
        // TODO: 2/9/2020 validation

        String jsonName = "\"name\":\"" + name + "\"";
        String jsonFamily = "\"family\":\"" + family + "\"";
        String jsonNationalCode = "\"nationalCode\":\"" + nationalCode + "\"";

        String result = "{"
                + jsonName + ", "
                + jsonFamily + ", "
                + jsonNationalCode +
                "}";
        return result;
    }

    public static String jasonCardPasswordInfo(String expirationDate, String cvv2, String password, String secondPassword){
        // TODO: 2/9/2020 validations

        String jsonExpirationDate = "\"expirationDate\":\"" + expirationDate + "\"";
        String jsonCvv2= "\"cvv2\":\"" + cvv2 + "\"";
        String jsonPassword= "\"password\":\"" + password + "\"";
        String jsonSecondPassword= "\"secondPassword\":" + (secondPassword == null? null: ("\""+secondPassword+"\"")) + "";

        String result = "{"
                + jsonExpirationDate + ", "
                + jsonCvv2 + ", "
                + jsonPassword + ", "
                + jsonSecondPassword +
                "}";

        return result;
    }

    public static String jsonCard(String cardNumber, Boolean isActive){
        // TODO: 2/9/2020 validations

        String jsonCardNumber = "\"cardNumber\":\"" + cardNumber + "\"";
        String jsonIsActive= "\"isActive\":" + isActive ;

        String result = "{"
                + jsonCardNumber + ", "
                + jsonIsActive +
                "}";

        return result;
    }

    public static String jsonTransaction(TransactionType transactionType, String date, Long amount, Boolean isSuccessful, String description){
        // TODO: 2/9/2020 validations

        String jsonTransactionType = "\"transactionType\":\"" + transactionType + "\"";
        String jsonDate= "\"date\":\"" + date + "\"";
        String jsonAmount= "\"amount\":" + amount + "";
        String jsonIsSuccessful= "\"isSuccessful\":" + isSuccessful + "";
        String jsonDescription= "\"description\":\"" + description + "\"";

        String result = "{"
                + jsonTransactionType + ", "
                + jsonDate + ", "
                + jsonAmount + ", "
                + jsonIsSuccessful + ", "
                + jsonDescription +
                "}";

        return result;
    }

    public static String jsonAccount(String accountNumber, Boolean isActive, Long balance){
        // TODO: 2/9/2020 validations

        String jsonAccountNumber = "\"accountNumber\":\"" + accountNumber + "\"";
        String jsonIsActive= "\"isActive\":" + isActive + "";
        String jsonBalance= "\"balance\":" + balance + "";


        String result = "{"
                + jsonAccountNumber + ", "
                + jsonIsActive + ", "
                + jsonBalance +
                "}";

        return result;
    }

    public static String jsonCustomer(String customerNumber){
        // TODO: 2/9/2020 validations

        String jsonCustomerNumber = "\"customerNumber\":\"" + customerNumber + "\"";

        String result = "{"
                + jsonCustomerNumber +
                "}";

        return result;
    }

}
