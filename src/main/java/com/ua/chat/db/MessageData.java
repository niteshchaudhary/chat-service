package com.ua.chat.db;

import com.ua.chat.core.Message;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

public class MessageData extends AbstractDAO<Message> {

    @PersistenceContext
    EntityManager em;
    public MessageData(SessionFactory factory) {
        super(factory);
    }

    public Optional<Message> findById(int id) {
        return Optional.ofNullable(get(id));
    }

    public List<Message> findByName(String name) {
        //"com.ua.chat.core.Message.findByName"
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

        // javax.persistence.Query q = criteriaBuilder.createQuery("SELECT p FROM Message p where p.username LIKE :"+name+"");
        System.out.println("111111111111111111111");
        //   System.out.println(q);
        return null ;//list((Query<Message>) criteriaQuery().where());
    }

    public Message create(Message message) {
        System.out.println("here");
        System.out.println(message);

        return persist(message);
    }

    @SuppressWarnings("unchecked")
    public List<Message> findAll() {
        return list((Query<Message>) namedQuery("com.ua.chat.core.Message.findAll"));
    }
}
