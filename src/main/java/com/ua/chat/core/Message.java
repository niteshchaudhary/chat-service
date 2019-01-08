package com.ua.chat.core;

import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "messages")
@NamedQueries(
    {
        @NamedQuery(
            name = "com.ua.chat.core.Message.findAll",
            query = "SELECT p FROM Message p"
        ),
        @NamedQuery(
            name = "com.ua.chat.core.Message.findByName",
            query = "SELECT p FROM Message p where p.username LIKE :username"
        ),
        @NamedQuery(
                name = "com.ua.chat.core.Message.findById",
                query = "SELECT p FROM Message p where p.username = :id"
            )
    })
public class Message {
    @Id
    private int id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "timeout")
    private Integer timeout;

    @Column(name = "expired")
    private Boolean expired = false;

    @Column(name = "expirationdate")
    private DateTime expirationdate;

    public Message() {
    }

    public Message(String username, String text, Integer timeout, Boolean expired, DateTime expirationdate) {
        this.username = username;
        this.text = text;
        this.timeout = timeout;
        this.expired = expired;
        this.expirationdate = expirationdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    public DateTime getExpirationdate() {
        return expirationdate;
    }

    public void setExpirationdate(DateTime expirationdate) {
        this.expirationdate = expirationdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Message)) {
            return false;
        }

        final Message that = (Message) o;

        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.username, that.username) &&
                Objects.equals(this.text, that.text) &&
                Objects.equals(this.timeout, that.timeout) &&
                Objects.equals(this.expired, that.expired) &&
                Objects.equals(this.expirationdate, that.expirationdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, text, timeout, expired, expirationdate);
    }
}
