package ir.maktab.homeworks.hw14.repositories;

import ir.maktab.homeworks.hw14.config.hibernate.HibernateUtil;
import ir.maktab.homeworks.hw14.config.hibernate.repositories.CrudRepository;
import ir.maktab.homeworks.hw14.entities.Account;
import ir.maktab.homeworks.hw14.entities.CardPasswordInfo;
import org.hibernate.Session;

public class CardPasswordInfoRepository extends CrudRepository<CardPasswordInfo, Long> {
    @Override
    protected Class<CardPasswordInfo> getEntityClass() {
        return CardPasswordInfo.class;
    }

    @Override
    protected Session getSession() {
        return HibernateUtil.getSession();
    }

    private static CardPasswordInfoRepository cardPasswordInfoRepository;
    public static CardPasswordInfoRepository getInstance(){
        if (cardPasswordInfoRepository == null)
            cardPasswordInfoRepository = new CardPasswordInfoRepository();
        return cardPasswordInfoRepository;
    }
}