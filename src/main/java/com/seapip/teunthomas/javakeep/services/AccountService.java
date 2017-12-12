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
    
    @Inject
    public AccountRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    @Authorization
    public Response getById(@Context ContainerRequestContext requestContext, @PathParam("id") long id) {
        com.seapip.teunthomas.javakeep.dto.Account account = repository.getById(id);
        return Response.status(account == null ? 404 : 200).entity(account).build();
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{email}")
    @Authorization
    public Response getByEmail(@Context ContainerRequestContext requestContext, @PathParam("email") String email) {
        com.seapip.teunthomas.javakeep.dto.Account account = repository.getByEmail(email);
        return Response.status(account == null ? 404 : 200).entity(account).build();
    }
   
    
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
