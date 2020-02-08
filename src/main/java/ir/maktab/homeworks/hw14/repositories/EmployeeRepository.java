package ir.maktab.homeworks.hw14.repositories;

import ir.maktab.homeworks.hw14.config.hibernate.HibernateUtil;
import ir.maktab.homeworks.hw14.config.hibernate.repositories.CrudRepository;
import ir.maktab.homeworks.hw14.entities.Account;
import ir.maktab.homeworks.hw14.entities.Employee;
import org.hibernate.Session;

public class EmployeeRepository extends CrudRepository<Employee, Long> {
    @Override
    protected Class<Employee> getEntityClass() {
        return Employee.class;
    }

    @Override
    protected Session getSession() {
        return HibernateUtil.getSession();
    }

    private static EmployeeRepository employeeRepository;
    public static EmployeeRepository getInstance(){
        if (employeeRepository == null)
            employeeRepository = new EmployeeRepository();
        return employeeRepository;
    }
}