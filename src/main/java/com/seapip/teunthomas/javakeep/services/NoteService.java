package com.seapip.teunthomas.javakeep.services;

import com.seapip.teunthomas.javakeep.dto.Account;
import com.seapip.teunthomas.javakeep.dto.Note;
import com.seapip.teunthomas.javakeep.dto.SharedNote;
import com.seapip.teunthomas.javakeep.entities.Shareable;
import com.seapip.teunthomas.javakeep.filters.Authorization;
import com.seapip.teunthomas.javakeep.repositories.NoteRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@Path("/notes")
public class NoteService {

    @Inject
    public EntityManager entityManager;

    @Inject
    public NoteRepository repository;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Authorization
    public Response create(@Context ContainerRequestContext requestContext, Note note) {
        note.setAccount(new Account((Long) requestContext.getProperty("id")));
        note = repository.create(note);
        return Response.status(200).entity(note).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    @Authorization
    public Response getById(@Context ContainerRequestContext requestContext, @PathParam("id") long id) {
        Note note = repository.getById(id, (Long) requestContext.getProperty("id"));
        return Response.status(note == null ? 404 : 200).entity(note).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Authorization
    public Response getAll(@Context ContainerRequestContext requestContext) {
        List<Note> notes = repository.getAll((Long) requestContext.getProperty("id"));
        return Response.status(200).entity(notes).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}/share")
    @Authorization
    public Response share(@Context ContainerRequestContext requestContext, @PathParam("id") long id, @QueryParam("permission") Shareable.Permission permission) {
        UUID token = repository.share(id, permission == null ? Shareable.Permission.READ : permission, (Long) requestContext.getProperty("id"));
        return Response.status(200).entity(token.toString()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("shared/{token}")
    public Response getByToken(@Context ContainerRequestContext requestContext, @PathParam("token") String token) {
        SharedNote note = repository.getByToken(UUID.fromString(token));
        return Response.status(note == null ? 404 : 200).entity(note).build();
    }
}
