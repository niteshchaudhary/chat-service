package com.ua.chat.api;

public abstract class BaseMessage {
    String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}
