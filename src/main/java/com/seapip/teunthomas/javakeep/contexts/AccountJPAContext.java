package com.seapip.teunthomas.javakeep.contexts;


import com.seapip.teunthomas.javakeep.dao.Note;
import com.seapip.teunthomas.javakeep.entities.Noteable;

import javax.persistence.EntityManager;

public class AccountJPAContext implements AccountContext {
    private EntityManager entityManager;

    public AccountJPAContext(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Accountable getById(Long id, Long accountId) {
        return entityManager
                .createNamedQuery("Account.getById", Account.class)
                .setParameter("id", id)
                .setParameter("accountId", accountId)
                .getSingleResult();

    }
}
