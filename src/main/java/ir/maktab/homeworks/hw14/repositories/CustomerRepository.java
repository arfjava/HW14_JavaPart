package ir.maktab.homeworks.hw14.repositories;

import ir.maktab.homeworks.hw14.config.hibernate.HibernateUtil;
import ir.maktab.homeworks.hw14.config.hibernate.repositories.CrudRepository;
import ir.maktab.homeworks.hw14.entities.Customer;
import org.hibernate.Session;

import java.util.stream.Collectors;

public class CustomerRepository extends CrudRepository<Customer, Long> {
    @Override
    protected Class<Customer> getEntityClass() {
        return Customer.class;
    }

    @Override
    protected Session getSession() {
        return HibernateUtil.getSession();
    }

    private static CustomerRepository customerRepository;
    public static CustomerRepository getInstance(){
        if (customerRepository == null)
            customerRepository = new CustomerRepository();
        return customerRepository;
    }
    
    public Customer findByCustomerNumber(String customerNumber){

        // TODO: 2/9/2020 validation 
        Customer result = null;
        result = findAll().stream().filter(customer -> customer.getCustomerNumber().equals(customerNumber))
                .collect(Collectors.toList()).get(0);
        return result;
    }

    public boolean isCustomerNumberExisting(String customerNumber){
        try {
            findByCustomerNumber(customerNumber).equals(null);
            return true;
        }catch (Exception e){ return false;}
    }
}