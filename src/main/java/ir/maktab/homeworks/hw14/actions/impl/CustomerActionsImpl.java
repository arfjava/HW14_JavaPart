package ir.maktab.homeworks.hw14.actions.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.maktab.homeworks.hw14.actions.abstraction.CustomerActions;
import ir.maktab.homeworks.hw14.entities.*;
import ir.maktab.homeworks.hw14.inputs.Input;
import ir.maktab.homeworks.hw14.repositories.BankBranchRepository;
import ir.maktab.homeworks.hw14.repositories.CustomerRepository;
import ir.maktab.homeworks.hw14.utilities.AutoGenerateNumbers;
import ir.maktab.homeworks.hw14.utilities.JsonFactory;

import java.util.stream.Collectors;

public class CustomerActionsImpl implements CustomerActions {

    @Override
    public Customer add() throws JsonProcessingException {
        Customer result;

        Long branchId = Input.branchId();
        String name = Input.name();
        String family = Input.family();

        String nationalCode;
        boolean nationalCodeExtraValidation;
        do {
            nationalCode = Input.nationalCode();
            String finalNationalCode = nationalCode;
            nationalCodeExtraValidation = BankBranchRepository.getInstance().findById(branchId).getCustomers().stream()
                    .map(customer -> customer.getPersonalInfo().getNationalCode())
                    .filter(i -> i.equals(finalNationalCode)).collect(Collectors.toList()).size() == 0;
            if (!nationalCodeExtraValidation)
                System.out.println("Entered National Code is Already Registered at This Branch!");
        }while (!nationalCodeExtraValidation);

        String country = Input.country();
        String city = Input.city();
        String street = Input.street();
        String zipCode = Input.zipCode();



        BankBranch branch = BankBranchRepository.getInstance().findById(branchId);

        String customerNumber = AutoGenerateNumbers.generateNewCustomerNumber(branchId);

        ObjectMapper mapper = new ObjectMapper();

        Address address = mapper.readValue(JsonFactory.jsonAddress(country, city, street, zipCode)
                , new TypeReference<Address>() {
                });
        PersonalInfo personalInfo = mapper.readValue(JsonFactory.jsonPersonalInfo(name, family, nationalCode)
                , new TypeReference<PersonalInfo>() {
                });
        Customer customer = mapper.readValue(JsonFactory.jsonCustomer(customerNumber)
                , new TypeReference<Customer>() {
                });

        personalInfo.setAddress(address);
        customer.setPersonalInfo(personalInfo);
        branch.getCustomers().add(customer);
        customer.setBranch(branch);

        BankBranchRepository.getInstance().update(branch);

        System.out.println("Customer Created! Your Customer Number: " + customerNumber);

        System.out.println("You Need to Create an Account Because Each Customer Must Have One Active Account At Least!");
        new AccountActionsImpl().add();

        return CustomerRepository.getInstance().findByCustomerNumber(customerNumber);
    }

}
