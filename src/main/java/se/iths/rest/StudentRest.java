package se.iths.rest;

import se.iths.entity.IdNotFoundException;
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

        try{
            studentService.createStudent(student);
        }
        catch (Exception e){
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity("You need to create a student with firstName, lastName and email! Please try again")
                    .build());
        }
        return Response.status(Response.Status.CREATED).entity(student).build();
    }
    @Path("")
    @GET
    public Response getAllStudents(){
        List<Student> foundStudents = studentService.getAllStudents();

        if(foundStudents.isEmpty()){
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("The database is empty.")
                    .build());
        }
        return Response.ok(foundStudents).build();
    }

    @Path("{id}")
    @GET
    public Response getStudent(@PathParam("id") Long id){
        Student foundStudent = studentService.findStudent(id);
        if(foundStudent == null){
            throw new IdNotFoundException("No student with id: " + id);
        }
        return Response.ok(foundStudent).build();
    }

    @Path("{id}")
    @PATCH
    public Response updateFirstName(@PathParam("id") Long id, @QueryParam("firstName") String firstName){
        Student updateStudent;
        try{
         updateStudent = studentService.updateFirstName(id, firstName);}
        catch (Exception e){
            throw new BadRequestException(Response.status(Response.Status.BAD_REQUEST)
                    .entity("Student with id " + id + " doesn't exist. Try update a Student with a different id!")
                    .build());
        }
        return Response.ok(updateStudent).build();
    }

    @Path("delete/{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id){
        try{studentService.deleteStudent(id);}
        catch (Exception e){
            throw new BadRequestException(Response.status(Response.Status.BAD_REQUEST)
                    .entity("Student with id " + id + " doesn't exist. Try delete a Student with a different id!")
                    .build());
        }
        String message = "Student Deleted.";
        return Response.ok(message).build();
    }

    @Path("getbylastname")
    @GET
    public Response getStudentByLastName(@QueryParam("lastName") String lastName){
        List<Student> foundStudents =  studentService.findStudentWithLastName(lastName);
        if(foundStudents.isEmpty()){
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("Student with lastname " + lastName  + " was not found in database.").
                    build());
        }

        return Response.ok(foundStudents).build();
    }




    //Frågor till Pontus:
    //
    // Om man gör patch varför kan man inte uppdatera en del av metoden när man
    //har alla värden i samma metod för att uppdatera?  Måste man göra en för varje värde man vill uppdatera
    //eller kan man skriva logik som gör att man kan uppdatera en itaget?
/*    @Path("updateStudent/{id}")
    @PATCH
    public Response updateStudent(@PathParam("id") Long id, @QueryParam("firstName") String firstName
    , @QueryParam("lastName") String lastName, @QueryParam("email") String email,
                                  @QueryParam("phoneNumber") String phoneNumber){
        Student updateStudent = studentService.updateStudent(id, Student);
        return Response.ok(updateStudent).build();
    }*/

    //Varför ska felmeddelanden returneras i JSON??
}
