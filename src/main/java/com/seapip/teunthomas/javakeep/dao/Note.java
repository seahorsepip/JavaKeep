package com.seapip.teunthomas.javakeep.dao;

import com.seapip.teunthomas.javakeep.entities.Accountable;
import com.seapip.teunthomas.javakeep.entities.Noteable;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Note.getById", query = "SELECT n FROM Note n WHERE n.id = :id AND n.account.id = :accountId"),
        @NamedQuery(name = "Note.getAll", query = "SELECT n FROM Note n WHERE n.account.id = :accountId")
})
public class Note implements Noteable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private Date date;
    @OneToMany(mappedBy = "note")
    private List<SharedNote> shares;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId")
    private Account account;

    public Note() {
    }

    public Note(Long id) {
        this.id = id;
    }

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

    public void setTitle(String name) {
        this.title = name;
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
    public Accountable getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<SharedNote> getShares() {
        return shares;
    }

    public void setShares(List<SharedNote> shares) {
        this.shares = shares;
    }
}
