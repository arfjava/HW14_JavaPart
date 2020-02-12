package ir.maktab.homeworks.hw14.repositories;

import ir.maktab.homeworks.hw14.config.hibernate.HibernateUtil;
import ir.maktab.homeworks.hw14.config.hibernate.repositories.CrudRepository;
import ir.maktab.homeworks.hw14.entities.Manager;
import org.hibernate.Session;

public class ManagerRepository extends CrudRepository<Manager, Long> {
    @Override
    protected Class<Manager> getEntityClass() {
        return Manager.class;
    }

    @Override
    protected Session getSession() {
        return HibernateUtil.getSession();
    }

    private static ManagerRepository managerRepository;
    public static ManagerRepository getInstance(){
        if (managerRepository == null)
            managerRepository = new ManagerRepository();
        return managerRepository;
    }
}