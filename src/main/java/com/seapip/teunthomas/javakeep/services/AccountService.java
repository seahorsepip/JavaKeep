package com.seapip.teunthomas.javakeep.services;

import com.seapip.teunthomas.javakeep.dto.Account;
import com.seapip.teunthomas.javakeep.repositories.AccountRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/accounts")
public class AccountService {

    @Inject
    public AccountRepository repository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Account account) {
        Account a = repository.create(account);
        return Response.status(200).entity(a).build();
    }
}
