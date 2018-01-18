package com.seapip.teunthomas.javakeep.services;

import com.seapip.teunthomas.javakeep.dto.Note;
import com.seapip.teunthomas.javakeep.dto.SharedNote;
import com.seapip.teunthomas.javakeep.entities.Shareable;
import com.seapip.teunthomas.javakeep.filters.Authorization;
import com.seapip.teunthomas.javakeep.repositories.NoteRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@Path("/notes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FolderService {

    @Inject
    private NoteRepository repository;

    @POST
    @Authorization
    public Response create(@Context ContainerRequestContext requestContext, Note note) {
        return Response.status(200).entity(repository.create(note, (Long) requestContext.getProperty("id"))).build();
    }

    @GET
    @Path("{id}")
    @Authorization
    public Response getById(@Context ContainerRequestContext requestContext, @PathParam("id") long id) {
        Note note = repository.getById(id, (Long) requestContext.getProperty("id"));
        return Response.status(note == null ? 404 : 200).entity(note).build();
    }

    @GET
    @Authorization
    public Response getAll(@Context ContainerRequestContext requestContext) {
        List<Note> notes = repository.getAll((Long) requestContext.getProperty("id"));
        return Response.status(200).entity(notes).build();
    }

    @POST
    @Path("{id}/share")
    @Authorization
    public Response share(@Context ContainerRequestContext requestContext, @PathParam("id") long id, @QueryParam("permission") Shareable.Permission permission) {
        UUID token = repository.share(id, permission == null ? Shareable.Permission.READ : permission, (Long) requestContext.getProperty("id"));
        return Response.status(200).entity(token.toString()).build();
    }

    @GET
    @Path("shared/{token}")
    public Response getByToken(@Context ContainerRequestContext requestContext, @PathParam("token") String token) {
        SharedNote note = repository.getByToken(UUID.fromString(token));
        return Response.status(note == null ? 404 : 200).entity(note).build();
    }

    @POST
    @Path("{id}")
    @Authorization
    public Response update(@Context ContainerRequestContext requestContext, @PathParam("id") Long id, Note note) {
        note.setId(id);
        repository.update(note, (Long) requestContext.getProperty("id"));
        return Response.status(200).build();
    }

    @POST
    @Path("shared/{token}")
    public Response update(@Context ContainerRequestContext requestContext, @PathParam("token") String token, Note note) {
        repository.update(note, UUID.fromString(token));
        return Response.status(200).build();
    }

    @DELETE
    @Path("{id}")
    @Authorization
    public Response update(@Context ContainerRequestContext requestContext, @PathParam("id") Long id) {
        repository.delete(id, (Long) requestContext.getProperty("id"));
        return Response.status(200).build();
    }
}
