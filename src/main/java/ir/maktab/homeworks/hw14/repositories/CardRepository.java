package ir.maktab.homeworks.hw14.repositories;

import ir.maktab.homeworks.hw14.config.hibernate.HibernateUtil;
import ir.maktab.homeworks.hw14.config.hibernate.repositories.CrudRepository;
import ir.maktab.homeworks.hw14.entities.Card;
import ir.maktab.homeworks.hw14.entities.Transaction;
import org.hibernate.Session;

public class CardRepository extends CrudRepository<Card, Long> {
    @Override
    protected Class<Card> getEntityClass() {
        return Card.class;
    }

    @Override
    protected Session getSession() {
        return HibernateUtil.getSession();
    }

    private static CardRepository cardRepository;
    public static CardRepository getInstance(){
        if (cardRepository == null)
            cardRepository = new CardRepository();
        return cardRepository;
    }
}