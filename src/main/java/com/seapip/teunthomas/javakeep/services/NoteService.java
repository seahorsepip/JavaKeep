package com.seapip.teunthomas.javakeep.services;

import com.seapip.teunthomas.javakeep.dao.Account;
import com.seapip.teunthomas.javakeep.dao.Note;
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

@Path("/notes")
public class NoteService {

    @Inject
    public EntityManager entityManager;

    @Inject
    public NoteRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    @Authorization
    public Response getById(@Context ContainerRequestContext requestContext, @PathParam("id") long id) {
        com.seapip.teunthomas.javakeep.dto.Note note = repository.getById(id, (Long) requestContext.getProperty("id"));
        return Response.status(note == null ? 404 : 200).entity(note).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Authorization
    public Response getAll(@Context ContainerRequestContext requestContext) {
        List<Note> notes = entityManager
                .createNamedQuery("Note.getAll", Note.class)
                .setParameter("accountId", requestContext.getProperty("id"))
                .getResultList();
        return Response.status(notes.size() > 0 ? 200 : 404).entity(notes).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Authorization
    public Response create(@Context ContainerRequestContext requestContext, Note note) {
        note.setAccount(new Account((Long) requestContext.getProperty("id")));
        entityManager.getTransaction().begin();
        entityManager.persist(note);
        entityManager.clear();
        entityManager.getTransaction().commit();
        return Response.status(200).build();
    }
}
