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

        return Response.status(Response.Status.CREATED).entity(student).build();

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

    @Path("updatestudent/{id}")
    @PATCH
    public Response updateFirstName(@PathParam("id") Long id, @QueryParam("firstName") String firstName){
        Student updateStudent = studentService.updateFirstName(id, firstName);
        return Response.ok(updateStudent).build();
    }

    @Path("delete/{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id){
        studentService.deleteStudent(id);
        String message = "Student Deleted";
        return Response.ok(message).build();
    }

    @Path("getbylastname")
    @GET
    public Response getStudentByLastName(@QueryParam("lastName") String lastName){
        List<Student> foundStudents =  studentService.findStudentWithLastName(lastName);
        //Varför funkar if null med id men inte med lastName??
        if(foundStudents == null){
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("Student with lastname " + lastName  + " was not found in database.")
                    .type(MediaType.TEXT_PLAIN_TYPE).build());
        }
        return Response.ok(foundStudents, MediaType.APPLICATION_JSON).build();
    }




    //Fråga till Pontus: Om man gör patch varför kan man inte uppdatera en del av metoden när man
    //har alla värden i samma metod för att uppdatera?  Måste man göra en för varje värde man vill uppdatera
    //eller kan man skriva logik som gör att man kan uppdatera en itaget?
/*    @Path("updateStudent/{id}")
    @PATCH
    public Response updateStudent(@PathParam("id") Long id, @QueryParam("firstName") String firstName
    , @QueryParam("lastName") String lastName, @QueryParam("email") String email,
                                  @QueryParam("phoneNumber") String phoneNumber){
        Student updateStudent = studentService.updateStudent(id, firstName, lastName, email, phoneNumber);
        return Response.ok(updateStudent).build();
    }*/

    //Varför funkar inte min try catch?
/*            catch (BadRequestException e){
        throw new BadRequestException(Response.status(Response.Status.BAD_REQUEST)
                .entity("You need to put in studens first name")
                .type(MediaType.TEXT_PLAIN_TYPE).build());*/
}
