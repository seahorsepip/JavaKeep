package com.seapip.teunthomas.javakeep.services;

import com.seapip.teunthomas.javakeep.dao.Account;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/accounts")
public class AccountService {

    @Inject
    public EntityManager entityManager;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Account account) {
        entityManager.getTransaction().begin();
        entityManager.persist(account);
        entityManager.clear();
        entityManager.getTransaction().commit();
        return Response.status(200).build();
    }
}
