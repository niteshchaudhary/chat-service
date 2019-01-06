package com.ua.chat.db;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.ua.chat.core.Message;

import io.dropwizard.hibernate.AbstractDAO;

public class ChatMessageDAO extends AbstractDAO<Message> {
    public ChatMessageDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<Message> findById(int id) {
        return Optional.ofNullable(get(id));
    }

    public Message create(Message message) {
        Random rand = new Random();
        int value = rand.nextInt(100);
        message.setId(value);

        if (message.getTimeout() == 0) {
            message.setTimeout(60);
        }
        return persist(message);
    }

    @SuppressWarnings("unchecked")
    public List<Message> findAll() {
        return list((Query<Message>) namedQuery("com.ua.chat.core.Message.findAll"));
    }

    @SuppressWarnings("unchecked")
    public List<Message> findByName(String name) {

    	Query<Message> query = namedQuery("com.ua.chat.core.Message.findByName");
    	query.setString("username", name);

        //List of messages which are not expired
        List<Message> messagesNotExpired = list(query).stream()
                .filter(messageList -> messageList.getExpired() == false).collect(Collectors.toList());

        //List to set expired messages from false to true ) the ones which are being viewed now
        List<Message> messages = list(query);
        messages.stream()
                .filter(messageList -> messageList.getExpired() == false)
                .forEach(messageList -> messageList.setExpired(true));


        return messagesNotExpired;
    }

}
