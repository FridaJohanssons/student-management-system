package se.iths.rest;

import se.iths.entity.Teacher;
import se.iths.service.TeacherService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("teachers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TeacherRest {

    @Inject
    TeacherService teacherService;

    @Path("")
    @POST
    public Response createTeachers(Teacher teacher){
        teacherService.createTeacher(teacher);

        return Response.status(Response.Status.CREATED).entity(teacher).build();
    }

    @Path("")
    @GET
    public Response getAllTeachers(){
        List<Teacher> foundTeachers = teacherService.getAllTeachers();

        return Response.ok(foundTeachers).build();
    }
}