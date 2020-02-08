package ir.maktab.homeworks.hw14.repositories;

import ir.maktab.homeworks.hw14.config.hibernate.HibernateUtil;
import ir.maktab.homeworks.hw14.config.hibernate.repositories.CrudRepository;
import ir.maktab.homeworks.hw14.entities.Transaction;
import org.hibernate.Session;

public class TransactionRepository extends CrudRepository<Transaction, Long> {
    @Override
    protected Class<Transaction> getEntityClass() {
        return Transaction.class;
    }

    @Override
    protected Session getSession() {
        return HibernateUtil.getSession();
    }

    private static TransactionRepository transactionRepository;
    public static TransactionRepository getInstance(){
        if (transactionRepository == null)
            transactionRepository = new TransactionRepository();
        return transactionRepository;
    }
}