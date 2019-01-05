package com.ua.chat.api;

public class Message extends BaseMessage{

    Integer id;

    public Message(Integer id) {
        super();
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
