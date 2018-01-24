package com.seapip.teunthomas.javakeep.services;

import com.seapip.teunthomas.javakeep.dto.Note;
import com.seapip.teunthomas.javakeep.filters.Authorization;
import com.seapip.teunthomas.javakeep.repositories.NoteRepository;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/notes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NoteService {

    @Inject
    private NoteRepository repository;

    @POST
    @Authorization
    public Response create(@Context ContainerRequestContext requestContext, Note note, @QueryParam("password") String password) {
        return Response.status(200).entity(repository.create(note, (Long) requestContext.getProperty("id"), password)).build();
    }

    @GET
    @Path("{id}")
    @Authorization
    public Response getById(@Context ContainerRequestContext requestContext, @PathParam("id") long id, @QueryParam("password") String password) {
        try {
            Note note = repository.getById(id, (Long) requestContext.getProperty("id"), password);
            return Response.status(note == null ? 404 : 200).entity(note).build();
        } catch (EncryptionOperationNotPossibleException e) {
            return Response.status(401).build();
        }
    }

    @GET
    @Authorization
    public Response getAll(@Context ContainerRequestContext requestContext) {
        List<Note> notes = repository.getAll((Long) requestContext.getProperty("id"));
        return Response.status(200).entity(notes).build();
    }

    /*
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
    */

    @POST
    @Path("{id}")
    @Authorization
    public Response update(@Context ContainerRequestContext requestContext, @PathParam("id") Long id, Note note, @QueryParam("password") String password) {
        note.setId(id);
        repository.update(note, (Long) requestContext.getProperty("id"), password);
        return Response.status(200).build();
    }

    /*
    @POST
    @Path("shared/{token}")
    public Response update(@Context ContainerRequestContext requestContext, @PathParam("token") String token, Note note) {
        repository.update(note, UUID.fromString(token));
        return Response.status(200).build();
    }
    */

    @DELETE
    @Path("{id}")
    @Authorization
    public Response delete(@Context ContainerRequestContext requestContext, @PathParam("id") Long id) {
        repository.delete(id, (Long) requestContext.getProperty("id"));
        return Response.status(200).build();
    }
}
