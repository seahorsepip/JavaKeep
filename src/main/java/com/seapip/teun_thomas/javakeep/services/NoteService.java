package com.seapip.teun_thomas.javakeep.services;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/notes")
public class NoteService {

    @GET
    public Response sayHello() {
        String output = "Hello, Thomas!";
        return Response.status(200).entity(output).build();
    }
}
