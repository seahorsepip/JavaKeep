package com.seapip.teunthomas.javakeep.contexts;


import com.seapip.teunthomas.javakeep.dao.Note;
import com.seapip.teunthomas.javakeep.entities.Noteable;

import javax.persistence.EntityManager;

public class NoteJPAContext implements NoteContext {
    private EntityManager entityManager;

    public NoteJPAContext(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Noteable getById(Long id, Long accountId) {
        return entityManager
                .createNamedQuery("Note.getById", Note.class)
                .setParameter("id", id)
                .setParameter("accountId", accountId)
                .getSingleResult();

    }
}
