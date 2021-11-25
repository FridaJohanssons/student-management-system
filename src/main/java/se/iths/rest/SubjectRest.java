package se.iths.rest;


import se.iths.entity.Subject;
import se.iths.service.SubjectService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("subjects")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SubjectRest {

    @Inject
    SubjectService subjectService;

    @Path("")
    @POST
    public Response createSubjects(Subject subject){
        subjectService.createSubject(subject);

        return Response.status(Response.Status.CREATED).entity(subject).build();
    }

    @Path("")
    @GET
    public Response getAllSubjects(){
        List<Subject> foundSubjects = subjectService.getAllSubjects();

        return Response.ok(foundSubjects).build();
    }
}