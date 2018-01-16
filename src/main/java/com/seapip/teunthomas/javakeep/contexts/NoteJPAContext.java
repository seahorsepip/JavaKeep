package com.seapip.teunthomas.javakeep.contexts;

import com.seapip.teunthomas.javakeep.dao.Account;
import com.seapip.teunthomas.javakeep.dao.Note;
import com.seapip.teunthomas.javakeep.dao.SharedNote;
import com.seapip.teunthomas.javakeep.entities.Noteable;
import com.seapip.teunthomas.javakeep.entities.Shareable;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

public class NoteJPAContext implements NoteContext {

    private EntityManager entityManager;

    public NoteJPAContext(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Note create(Noteable noteable) {
        Note note = new Note();
        note.setTitle(noteable.getTitle());
        note.setContent(noteable.getContent());
        note.setDate(noteable.getDate());
        note.setAccount(new Account(noteable.getAccount().getId()));
        entityManager.getTransaction().begin();
        entityManager.persist(note);
        entityManager.flush();
        entityManager.getTransaction().commit();
        return note;
    }

    @Override
    public Note getById(Long id, Long accountId) {
        return entityManager
                .createNamedQuery("Note.getById", Note.class)
                .setParameter("id", id)
                .setParameter("accountId", accountId)
                .getSingleResult();

    }

    @Override
    public List<Note> getAll(Long accountId) {
        return entityManager
                .createNamedQuery("Note.getAll", Note.class)
                .setParameter("accountId", accountId)
                .getResultList();
    }

    @Override
    public UUID share(Long id, Shareable.Permission permission, Long accountId) {
        SharedNote sharedNote = new SharedNote();
        sharedNote.setPermission(permission);
        sharedNote.setNote(getById(id, accountId));
        entityManager.getTransaction().begin();
        entityManager.persist(sharedNote);
        entityManager.flush();
        entityManager.getTransaction().commit();
        return sharedNote.getToken();
    }

    @Override
    public Shareable getByToken(UUID token) {
        return entityManager
                .createNamedQuery("SharedNote.getByToken", SharedNote.class)
                .setParameter("token", token)
                .getSingleResult();
    }
}
