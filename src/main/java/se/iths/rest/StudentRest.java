package se.iths.rest;

import se.iths.entity.Student;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {

    @Inject
    StudentService studentService;

    @Path("")
    @POST
    public Response createStudent(Student student){
        studentService.createStudent(student);
        return Response.ok(student).build();
    }
    @Path("")
    @GET
    public Response getAllStudents(){
        List<Student> foundStudents = studentService.getAllStudents();
        return Response.ok(foundStudents).build();
    }
    @Path("{id}")
    @GET
    public Response getStudent(@PathParam("id") Long id){
        Student foundStudent = studentService.findStudent(id);
        if(foundStudent == null){
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("Student with id " + id  + " was not found in database.")
                    .type(MediaType.TEXT_PLAIN_TYPE).build());
        }
        return Response.ok(foundStudent).build();
    }
}
