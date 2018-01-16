package com.seapip.teunthomas.javakeep.dto;

import com.seapip.teunthomas.javakeep.entities.Accountable;

public class Account implements Accountable {
    private Long id;
    private String email;
    private String password;

    public Account() {
    }

    public Account(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Account setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public Account setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public Account setPassword(String password) {
        this.password = password;
        return this;
    }
}
