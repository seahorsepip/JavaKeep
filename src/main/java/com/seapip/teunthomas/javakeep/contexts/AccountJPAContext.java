package com.seapip.teunthomas.javakeep.contexts;

import com.seapip.teunthomas.javakeep.dao.Account;
import com.seapip.teunthomas.javakeep.entities.Accountable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class AccountJPAContext extends JPAContext implements AccountContext {

    public AccountJPAContext(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    @Override
    public Account create(Accountable accountable) {
        Account account = new Account();
        account.setEmail(accountable.getEmail());
        account.setPassword(accountable.getPassword());
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(account);
        entityManager.getTransaction().commit();
        entityManager.close();
        return account;
    }

    @Override
    public Account getById(Long id) {
        EntityManager entityManager = getEntityManager();
        Account account = entityManager
                .createNamedQuery("Account.getById", Account.class)
                .setParameter("id", id)
                .getSingleResult();
        entityManager.close();
        return account;
    }

    @Override
    public Account getByEmail(String email) {
        EntityManager entityManager = getEntityManager();
        Account account = entityManager
                .createNamedQuery("Account.getByEmail", Account.class)
                .setParameter("email", email)
                .getSingleResult();
        entityManager.close();
        return account;
    }

    @Override
    public void delete(Long id) {
        EntityManager entityManager = getEntityManager();
        entityManager
                .createNamedQuery("Account.delete", Account.class)
                .setParameter("id", id)
                .executeUpdate();
        entityManager.close();
    }

    @Override
    public void update(String newEmail, String newPassword) {
        EntityManager entityManager = getEntityManager();
        entityManager
                .createNamedQuery("Account.update", Account.class)
                .setParameter("email", newEmail)
                .setParameter("password", newPassword)
                .executeUpdate();
        entityManager.close();
    }

}
