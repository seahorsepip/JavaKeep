package com.seapip.teunthomas.javakeep.filters;

import com.seapip.teunthomas.javakeep.dao.Account;
import org.glassfish.jersey.internal.util.Base64;

import javax.inject.Inject;
import javax.naming.AuthenticationException;
import javax.persistence.EntityManager;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Authorization
public class AuthorizationFilter implements ContainerRequestFilter, ContainerResponseFilter {

    @Inject
    public EntityManager entityManager;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        try {
            String[] authorization = Base64.decodeAsString(requestContext.getHeaderString("Authorization").split(" ")[1]).split(":");
            String email = authorization[0];
            String password = authorization[1];

            Account account = (Account) entityManager.createNamedQuery("Account.getByEmail").setParameter("email", email).getSingleResult();

            if (!account.getPassword().equals(password)) throw new AuthenticationException();
            requestContext.setProperty("id", account.getId());
        } catch (Exception e) {
            requestContext.abortWith(Response.status(401).build());
        }
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {

    }
}
