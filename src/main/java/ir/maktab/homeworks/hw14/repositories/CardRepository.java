package ir.maktab.homeworks.hw14.repositories;

import ir.maktab.homeworks.hw14.config.hibernate.HibernateUtil;
import ir.maktab.homeworks.hw14.config.hibernate.repositories.CrudRepository;
import ir.maktab.homeworks.hw14.entities.Account;
import ir.maktab.homeworks.hw14.entities.Card;
import ir.maktab.homeworks.hw14.entities.Transaction;
import org.hibernate.Session;

import java.util.stream.Collectors;

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

    public Card findByCardNumber(String cardNumber){
        Card result;

        result = findAll().stream().filter(card -> card.getCardNumber().equals(cardNumber))
                .collect(Collectors.toList()).get(0);
        // TODO: 2/9/2020 validations
        return result;
    }

    public Account findAccountByCardNumber(String cardNumber){
        // TODO: 2/9/2020 validation
        Account result;
        result = AccountRepository.getInstance().findAll().stream()
                .filter(account -> (account.getCard() == null? false: account.getCard().getCardNumber().equals(cardNumber)))
                .collect(Collectors.toList()).get(0);
        return result;
    }

    public boolean isCardNumberExisting(String cardNumber){
        try {
            findByCardNumber(cardNumber).equals("");
            return true;
        }catch (Exception e){
            return false;
        }
    }
}