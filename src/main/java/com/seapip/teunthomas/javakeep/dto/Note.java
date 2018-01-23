package com.seapip.teunthomas.javakeep.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Note implements com.seapip.teunthomas.javakeep.entities.Noteable {
    private Long id;
    private String title;
    private String content;
    private Date date;
    private Account account;

    @Override
    public Long getId() {
        return id;
    }

    public Note setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public Note setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public String getContent() {
        return content;
    }

    public Note setContent(String content) {
        this.content = content;
        return this;
    }

    @Override
    public Date getDate() {
        return date;
    }

    public Note setDate(Date date) {
        this.date = date;
        return this;
    }

    @Override
    public Account getAccount() {
        return account;
    }

    public Note setAccount(Account account) {
        this.account = account;
        return this;
    }

    public Type getType() {
        return Type.PLAINTEXT;
    }

    public enum Type {
        PLAINTEXT,
        ENCRYPTED
    }
}
