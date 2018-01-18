package com.seapip.teunthomas.javakeep.services;

import com.seapip.teunthomas.javakeep.dto.Account;
import com.seapip.teunthomas.javakeep.repositories.AccountRepository;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/accounts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AccountService {

    @Inject
    private AccountRepository repository;

    @POST
    public Response create(Account account) {
        try {
            return Response.status(200).entity(repository.create(account)).build();
        } catch (PersistenceException e) {
            return Response.status(409).build();
        }

    }
}
