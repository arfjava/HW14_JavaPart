package ir.maktab.homeworks.hw14.repositories;

import ir.maktab.homeworks.hw14.config.hibernate.HibernateUtil;
import ir.maktab.homeworks.hw14.config.hibernate.repositories.CrudRepository;
import ir.maktab.homeworks.hw14.entities.Account;
import ir.maktab.homeworks.hw14.entities.Transaction;
import org.hibernate.Session;

import java.util.stream.Collectors;

public class AccountRepository extends CrudRepository<Account, Long> {
    @Override
    protected Class<Account> getEntityClass() {
        return Account.class;
    }

    @Override
    protected Session getSession() {
        return HibernateUtil.getSession();
    }

    private static AccountRepository accountRepository;
    public static AccountRepository getInstance(){
        if (accountRepository == null)
            accountRepository = new AccountRepository();
        return accountRepository;
    }

    public Account findByAccountNumber(String accountNumber){
        Account result;

        result = findAll().stream().filter(account -> account.getAccountNumber().equals(accountNumber))
                .collect(Collectors.toList()).get(0);
        // TODO: 2/9/2020 validations
        return result;
    }

    public boolean isAccountNumberExisting(String accountNumber){
        try {
            findByAccountNumber(accountNumber).equals(null);
            return true;
        }catch (Exception e){return false;}
    }
}