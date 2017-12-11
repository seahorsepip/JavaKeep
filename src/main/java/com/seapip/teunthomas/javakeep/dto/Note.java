package com.seapip.teunthomas.javakeep.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.seapip.teunthomas.javakeep.entities.Noteable;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Note implements Noteable{
    private Long id;
    private String title;
    private String content;
    private Date date;
    private Account account;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
