package com.seapip.teunthomas.javakeep.contexts;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class JPAContext {
    private EntityManagerFactory entityManagerFactory;

    public JPAContext(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
