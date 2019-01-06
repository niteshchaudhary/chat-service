package com.ua.chat.db;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.ua.chat.core.Message;

import io.dropwizard.hibernate.AbstractDAO;

public class ChatMessageDAO extends AbstractDAO<Message> {
    public ChatMessageDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<Message> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    public Message create(Message person) {
        return persist(person);
    }

    @SuppressWarnings("unchecked")
    public List<Message> findAll() {
        return list((Query<Message>) namedQuery("com.ua.chat.core.Message.findAll"));
    }
    
    @SuppressWarnings("unchecked")
    public List<Message> findByName(String name) {
    	
    	Query<Message> query = namedQuery("com.ua.chat.core.Message.findByName");
    	query.setString("username", name);
        return list(query);
    }
    
    @SuppressWarnings("unchecked")
    public List<Message> findById(long id) {
    	Query<Message> query = namedQuery("com.ua.chat.core.Message.findById");
    	query.setLong("id", id);
        return list(query);
    }
}
