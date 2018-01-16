package com.seapip.teunthomas.javakeep.dao;

import com.seapip.teunthomas.javakeep.entities.Shareable;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NamedQueries({
        @NamedQuery(name = "SharedNote.getByToken", query = "SELECT s FROM SharedNote s WHERE s.token = :token")
})
public class SharedNote implements Shareable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID token;
    @Enumerated(EnumType.ORDINAL)
    private Permission permission;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "noteId")
    private Note note;

    public SharedNote() {
    }

    @Override
    public UUID getToken() {
        return token;
    }

    //@Override
    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }
}