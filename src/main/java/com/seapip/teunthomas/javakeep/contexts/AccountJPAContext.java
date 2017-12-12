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
    public Accountable getById(Long id) {
        return entityManager
                .createNamedQuery("Account.getById", Account.class)
                .setParameter("id", id)
                .getSingleResult();

    }
    
    @Override
    public Accountable getByEmail(String email) {
        return entityManager
                .createNamedQuery("Account.getByEmail", Account.class)
                .setParameter("email", email)
                .getSingleResult();
    }
    
    @Override
    public void delete(Long id) {
        entityManager
                .createNamedQuery("Account.delete", Account.class)
                .setParameter("id",id)
                .executeUpdate();
    }
    
    @Override
    public void update(String newEmail, String newPassword) {
        entityManager
                .createNamedQuery("Account.update", Account.class)
                .setParameter("email",newEmail)
                .setParameter("password", newPassword)
                .executeUpdate();
    }
    
}
