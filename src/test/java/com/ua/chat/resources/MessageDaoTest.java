package com.ua.chat.resources;

import com.ua.chat.core.Message;
import com.ua.chat.db.ChatMessageDAO;
import io.dropwizard.testing.junit.DAOTestRule;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class MessageDaoTest {
    @Rule
    public DAOTestRule daoTestRule = DAOTestRule.newBuilder()
            .addEntityClass(Message.class)
            .build();

    private ChatMessageDAO messageDAO;

    @Before
    public void setUp() throws Exception {
        messageDAO = new ChatMessageDAO(daoTestRule.getSessionFactory());
    }

    @Test
    public void createMessage() {
        final Message message = daoTestRule.inTransaction(() -> messageDAO.create(createMessage("new user", "new text")));
        assertThat(message.getId()).isGreaterThan(0);
        assertThat(message.getUsername()).isEqualTo("new user");
        assertThat(message.getText()).isEqualTo("new text");
        assertThat(messageDAO.findById(message.getId())).isEqualTo(Optional.of(message));
    }

    @Test
    public void findAll() {
        daoTestRule.inTransaction(() -> {
            messageDAO.create(createMessage("new user 1","new text 1"));
            messageDAO.create(createMessage("new user 2","new text 2"));
            messageDAO.create(createMessage("new user 3","new text 3"));
        });

        final List<Message> messages = messageDAO.findAll();
        assertThat(messages).extracting("username").containsOnly("new user 1", "new user 2", "new user 3");
        assertThat(messages).extracting("text").containsOnly("new text 1", "new text 2", "new text 3");
    }


    @Test
    public void findByName() {
        daoTestRule.inTransaction(() -> {
            messageDAO.create(createMessage("new user 10","new text 10"));
        });

        final List<Message> messages = messageDAO.findByName("new user 10");
        assertThat(messages).extracting("username").containsOnly("new user 10");
    }

    @Test
    public void findById() {
        final Message message = daoTestRule.inTransaction(() -> messageDAO.create(createMessage("new user 11", "new text 11")));


        final Optional<Message> messageResult = messageDAO.findById(message.getId());
        Assert.assertEquals(message.getId(), messageResult.get().getId());
    }

    private Message createMessage(String userName, String text) {
        Message message = new Message();
        message.setUsername(userName);
        message.setText(text);
        message.setExpirationdate(DateTime.now().plusDays(2));
        message.setTimeout(60);

        return message;
    }
}
