package com.seapip.teunthomas.javakeep.dto;

import com.seapip.teunthomas.javakeep.entities.Accountable;

public class Account implements Accountable {
    private Long id;
    private String email;
    private String password;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
