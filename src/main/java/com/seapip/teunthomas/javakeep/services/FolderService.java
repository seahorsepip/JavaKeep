package com.seapip.teunthomas.javakeep.services;

import com.seapip.teunthomas.javakeep.dto.Folder;
import com.seapip.teunthomas.javakeep.filters.Authorization;
import com.seapip.teunthomas.javakeep.repositories.FolderRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/folders")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FolderService {

    @Inject
    private FolderRepository repository;

    @POST
    @Authorization
    public Response create(@Context ContainerRequestContext requestContext, Folder folder) {
        return Response.status(200).entity(repository.create(folder, (Long) requestContext.getProperty("id"))).build();
    }

    @GET
    @Path("{id}")
    @Authorization
    public Response getById(@Context ContainerRequestContext requestContext, @PathParam("id") long id) {
        Folder folder = repository.getById(id, (Long) requestContext.getProperty("id"));
        return Response.status(folder == null ? 404 : 200).entity(folder).build();
    }

    @GET
    @Authorization
    public Response getAll(@Context ContainerRequestContext requestContext) {
        List<Folder> folders = repository.getAll((Long) requestContext.getProperty("id"));
        return Response.status(200).entity(folders).build();
    }

    @POST
    @Path("{id}/share")
    @Authorization
    public Response share(@Context ContainerRequestContext requestContext, @PathParam("id") long id, @QueryParam("email") String email) {
        repository.share(id, email, (Long) requestContext.getProperty("id"));
        return Response.status(200).build();
    }

    /*
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
*/
    @DELETE
    @Path("{id}")
    @Authorization
    public Response update(@Context ContainerRequestContext requestContext, @PathParam("id") Long id) {
        repository.delete(id, (Long) requestContext.getProperty("id"));
        return Response.status(200).build();
    }
}
