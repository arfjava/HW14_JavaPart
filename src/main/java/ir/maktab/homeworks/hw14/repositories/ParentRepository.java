//package ir.maktab.homeworks.hw14.repositories;
//
//import ir.maktab.homeworks.hw14.config.hibernate.HibernateUtil;
//import ir.maktab.homeworks.hw14.config.hibernate.repositories.CrudRepository;
//import ir.maktab.homeworks.hw14.entities.Account;
//import ir.maktab.homeworks.hw14.entities.Parent;
//import org.hibernate.Session;
//
//public class ParentRepository extends CrudRepository<Parent, Long> {
//    @Override
//    protected Class<Parent> getEntityClass() {
//        return Parent.class;
//    }
//
//    @Override
//    protected Session getSession() {
//        return HibernateUtil.getSession();
//    }
//
//    private static ParentRepository parentRepository;
//    public static ParentRepository getInstance(){
//        if (parentRepository == null)
//            parentRepository = new ParentRepository();
//        return parentRepository;
//    }
//}