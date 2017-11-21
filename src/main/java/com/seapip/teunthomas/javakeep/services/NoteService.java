package com.seapip.teunthomas.javakeep.services;

import com.seapip.teunthomas.javakeep.entities.Note;
import com.seapip.teunthomas.javakeep.filters.Authorization;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/notes")
public class NoteService {

    @Inject
    public EntityManager entityManager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getById(@PathParam("id") long id) {
        Note note = entityManager.find(Note.class, id);
        return Response.status(note == null ? 404 : 200).entity(note).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Authorization
    public Response getAll(@Context ContainerRequestContext requestContext) {
        long userId = (long) requestContext.getProperty("id");
        System.out.println(userId);
        List notes = entityManager.createNamedQuery("Note.getAll").getResultList();
        return Response.status(notes.size() > 0 ? 200 : 404).entity(notes).build();
    }
}
