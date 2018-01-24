package com.seapip.teunthomas.javakeep.dao;

import com.seapip.teunthomas.javakeep.entities.Accountable;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Note.getById", query = "SELECT n FROM Note n WHERE n.id = :id AND n.account.id = :accountId"),
        @NamedQuery(name = "Note.getAll", query = "SELECT n FROM Note n WHERE n.account.id = :accountId"),
        @NamedQuery(name = "Note.delete", query = "DELETE FROM Note n WHERE n.id = :id AND n.account.id = :accountId")
})
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class Note implements com.seapip.teunthomas.javakeep.entities.Noteable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "folderId")
    private Folder folder;

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

    @Override
    public com.seapip.teunthomas.javakeep.dto.Note.Type getType() {
        return com.seapip.teunthomas.javakeep.dto.Note.Type.PLAINTEXT;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
