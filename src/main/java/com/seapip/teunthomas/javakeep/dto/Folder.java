package com.seapip.teunthomas.javakeep.dto;

import com.seapip.teunthomas.javakeep.entities.Accountable;
import com.seapip.teunthomas.javakeep.entities.Folderable;
import com.seapip.teunthomas.javakeep.entities.Noteable;

import java.util.List;

public class Folder implements Folderable {
    private Long id;
    private String name;
    private List<Note> notes;
    private List<Account> accounts;

    @Override
    public Long getId() {
        return id;
    }

    public Folder setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public String getName() {
        return name;
    }

    public Folder setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public List<? extends Noteable> getNotes() {
        return notes;
    }

    public Folder setNotes(List<Note> notes) {
        this.notes = notes;
        return this;
    }

    @Override
    public List<? extends Accountable> getAccounts() {
        return accounts;
    }

    public Folder setAccounts(List<Account> accounts) {
        this.accounts = accounts;
        return this;
    }
}
