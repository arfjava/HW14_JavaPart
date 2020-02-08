package ir.maktab.homeworks.hw14.repositories;

import ir.maktab.homeworks.hw14.config.hibernate.HibernateUtil;
import ir.maktab.homeworks.hw14.config.hibernate.repositories.CrudRepository;
import ir.maktab.homeworks.hw14.entities.Customer;
import org.hibernate.Session;

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
}