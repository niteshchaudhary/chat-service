package com.ua.chat.api;


import java.util.List;

public class UserMessageResponse {

    List<Message> messages;

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
