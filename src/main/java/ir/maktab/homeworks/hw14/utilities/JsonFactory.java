package ir.maktab.homeworks.hw14.utilities;


public class JsonFactory {

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
