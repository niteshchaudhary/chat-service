package com.ua.chat.api;

//import io.swagger.annotations.ApiModelProperty;

import java.time.Instant;

public class ChatMessageResponse extends BaseMessage {

    String name;

    Instant expirationDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Instant expirationDate) {
        this.expirationDate = expirationDate;
    }

}
