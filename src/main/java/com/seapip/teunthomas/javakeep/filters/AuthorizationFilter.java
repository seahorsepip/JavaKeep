package com.seapip.teunthomas.javakeep.filters;

import com.seapip.teunthomas.javakeep.dto.Account;
import com.seapip.teunthomas.javakeep.repositories.AccountRepository;
import org.glassfish.jersey.internal.util.Base64;

import javax.inject.Inject;
import javax.naming.AuthenticationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Authorization
public class AuthorizationFilter implements ContainerRequestFilter {

    @Inject
    private AccountRepository repository;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        try {
            String[] authorization = Base64.decodeAsString(requestContext.getHeaderString("Authorization").split(" ")[1]).split(":");
            String email = authorization[0];
            String password = authorization[1];

            Account account = repository.getByEmail(email);

            if (!repository.verify(account, password)) throw new AuthenticationException();
            requestContext.setProperty("id", account.getId());
        } catch (Exception e) {
            requestContext.abortWith(Response.status(401).build());
        }
    }
}
