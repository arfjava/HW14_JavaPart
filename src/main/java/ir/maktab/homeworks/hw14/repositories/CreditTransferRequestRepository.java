//package ir.maktab.homeworks.hw14.repositories;
//
//import ir.maktab.homeworks.hw14.config.hibernate.HibernateUtil;
//import ir.maktab.homeworks.hw14.config.hibernate.repositories.CrudRepository;
//import ir.maktab.homeworks.hw14.entities.Account;
//import ir.maktab.homeworks.hw14.entities.CreditTransferRequest;
//import org.hibernate.Session;
//
//public class CreditTransferRequestRepository extends CrudRepository<CreditTransferRequest, Long> {
//    @Override
//    protected Class<CreditTransferRequest> getEntityClass() {
//        return CreditTransferRequest.class;
//    }
//
//    @Override
//    protected Session getSession() {
//        return HibernateUtil.getSession();
//    }
//
//    private static CreditTransferRequest creditTransferRequest;
//    public static CreditTransferRequest getInstance(){
//        if (creditTransferRequest == null)
//            creditTransferRequest = new CreditTransferRequest();
//        return creditTransferRequest;
//    }
//}