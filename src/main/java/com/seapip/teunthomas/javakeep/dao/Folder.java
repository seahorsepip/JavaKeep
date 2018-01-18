package com.seapip.teunthomas.javakeep.dao;

import com.seapip.teunthomas.javakeep.entities.Folderable;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Folder.getByAccount", query = "SELECT f FROM Folder f WHERE f.id = :id AND :account MEMBER OF f.accounts"),
        @NamedQuery(name = "Folder.delete", query = "DELETE FROM Folder f WHERE f.id = :id AND :account MEMBER OF f.accounts")
})
public class Folder implements Folderable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "folder")
    private List<Note> notes;
    @ManyToMany
    private List<Account> accounts;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<Note> getNotes() {
        return notes;
    }

    @Override
    public List<Account> getAccounts() {
        return accounts;
    }
}
