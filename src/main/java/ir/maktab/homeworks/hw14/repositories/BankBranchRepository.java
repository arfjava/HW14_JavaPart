package ir.maktab.homeworks.hw14.repositories;

import ir.maktab.homeworks.hw14.config.hibernate.HibernateUtil;
import ir.maktab.homeworks.hw14.config.hibernate.repositories.CrudRepository;
import ir.maktab.homeworks.hw14.entities.BankBranch;
import org.hibernate.Session;

public class BankBranchRepository extends CrudRepository<BankBranch, Long> {
    @Override
    protected Class<BankBranch> getEntityClass() {
        return BankBranch.class;
    }

    @Override
    protected Session getSession() {
        return HibernateUtil.getSession();
    }

    private static BankBranchRepository bankBranchRepository;
    public static BankBranchRepository getInstance(){
        if (bankBranchRepository == null)
            bankBranchRepository = new BankBranchRepository();
        return bankBranchRepository;
    }
}