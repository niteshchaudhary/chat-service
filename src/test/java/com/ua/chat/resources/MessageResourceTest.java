package com.ua.chat.resources;


import com.ua.chat.core.Message;
import com.ua.chat.db.ChatMessageDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MessageResourceTest {

    @Mock
    private ChatMessageDAO messageDAO;

    MessageResource messageResource;

    @Before
    public void setUp() {
        messageResource = new MessageResource(messageDAO);
    }

    @Test
    public void create() {
        when(messageDAO.create(any(Message.class))).thenReturn(new Message());
        messageResource.createMessage(new Message());
        verify(messageDAO, times(1)).create(any(Message.class));
    }

    @Test
    public void getByName() throws Exception {
        when(messageDAO.findByName("test name")).thenReturn(new ArrayList<Message>());
        messageResource.getTextsByName("test name");
        Mockito.verify(messageDAO, times(1)).findByName("test name");
    }

    @Test
    public void getById() throws Exception {
        when(messageDAO.findById(1)).thenReturn(Optional.of(new Message()));
        messageResource.getTextById(1);
        Mockito.verify(messageDAO, times(1)).findById(1);
    }

}
